package net.somta.juggle.core.executor;

import net.somta.juggle.core.FlowRuntimeContext;


/**
 * @author husong
 */
public interface IExecutor {

    /**
     * 执行流程
     * @param flowRuntimeContext
     */
    void execute(FlowRuntimeContext flowRuntimeContext);

    /**
     * 获取节点元素的执行器
     * @param flowRuntimeContext
     * @return
     */
    IExecutor getExecutor(FlowRuntimeContext flowRuntimeContext);
}
