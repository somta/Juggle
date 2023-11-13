package net.somta.juggle.core.dispatcher.impl;

import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.WorkRunnerImpl;
import net.somta.juggle.core.dispatcher.AbstractDispatcher;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.executor.FlowExecutor;
import net.somta.juggle.core.model.FlowResult;

/**
 * 默认基于内存的调度器
 * @author husong
 */
public class AsyncDispatcher extends AbstractDispatcher {

    private FlowExecutor flowExecutor;

    public AsyncDispatcher() {
        super(new WorkRunnerImpl().startWork());
        flowExecutor = new FlowExecutor();
    }

    @Override
    protected FlowResult doSend(FlowRuntimeContext flowRuntimeContext) {
        super.workRunner.postWork(() -> startFlow(flowRuntimeContext) );
        return new FlowResult().setStatus(FlowStatusEnum.INIT);
    }

    /**
     * 开始执行流程
     * @param flowRuntimeContext
     */
    private void startFlow(FlowRuntimeContext flowRuntimeContext){
        System.out.println("startFlow.......");
        flowExecutor.execute(flowRuntimeContext);
    }
}
