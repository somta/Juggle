package net.somta.juggle.core;

/**
 * 执行具体的任务
 */
public interface IWorkRunner {

    /**
     * 发布任务
     * @param runnable 处理请求的线程
     */
    void postWork(Runnable runnable);

}
