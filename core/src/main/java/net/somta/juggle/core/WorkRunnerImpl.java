package net.somta.juggle.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

public class WorkRunnerImpl extends Thread implements IWorkRunner {

    private final Logger logger = LoggerFactory.getLogger(WorkRunnerImpl.class);

    /**
     * 存储处理请求线程的队列
     */
    private LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

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

                    task.run();
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
