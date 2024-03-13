/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/

package net.somta.juggle.core;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author husong
 * @since 1.0.0
 */
public class WorkRunnerImpl extends Thread implements IWorkRunner {

    private final Logger logger = LoggerFactory.getLogger(WorkRunnerImpl.class);

    /**
     * 存储处理请求线程的队列
     */
    private LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

    private ThreadPoolExecutor executorService = new ThreadPoolExecutor(
            5,
            10,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(500),
            new BasicThreadFactory.Builder().namingPattern("flowWorkThreadFactory-").build());

    /**
     * 任务执行器是否在执行
     */
    private volatile boolean isRunning;

    public WorkRunnerImpl() {
        this.setDaemon(true);
    }

    public WorkRunnerImpl startWork() {
        isRunning = true;
        this.start();
        return this;
    }

    @Override
    public void run() {
        setName("WorkRunner-" + getId());
        while(isRunning) {
            try {
                Runnable task = workQueue.take();
                if (task != null) {
                    System.out.println("获取到一个任务了"+task);

                    //task.run();

                    executorService.execute(task);
                }
            } catch (Throwable e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void postWork(Runnable runnable) {
        try {
            workQueue.put(runnable);
        } catch (Throwable e) {
            logger.error(e.getMessage());
        }
    }
}
