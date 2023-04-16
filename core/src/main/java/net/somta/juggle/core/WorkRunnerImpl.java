package net.somta.juggle.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkRunnerImpl extends Thread implements IWorkRunner {

    private final Logger logger = LoggerFactory.getLogger(WorkRunnerImpl.class);

    /**
     * 存储处理请求线程的队列
     */
    private LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

    // todo 先用固定线程池池化一下，后面改成手动创建
    ExecutorService executorService =  Executors.newFixedThreadPool(2);

    /**
     * 任务执行器是否在执行
     */
    private volatile boolean isRunning;

    public WorkRunnerImpl() {
        System.out.println("WorkRunnerImpl init....");
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
