package net.somta.juggle.core.executor;

import net.somta.juggle.core.RuntimeContext;

public interface IExecutor {

    /**
     * 执行流程
     * @param runtimeContext
     */
    void execute(RuntimeContext runtimeContext);
    /**
     * 执行流程
     * @param runtimeContext
     */
    //void postExecute(RuntimeContext runtimeContext);


    /**
     * 获取节点元素的执行器
     * @param runtimeContext
     * @return
     */
    IExecutor getExecutor(RuntimeContext runtimeContext);
}
