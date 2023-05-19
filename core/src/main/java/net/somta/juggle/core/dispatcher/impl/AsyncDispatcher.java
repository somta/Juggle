package net.somta.juggle.core.dispatcher.impl;

import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.WorkRunnerImpl;
import net.somta.juggle.core.dispatcher.AbstractDispatcher;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.executor.FlowExecutor;
import net.somta.juggle.core.model.FlowResult;

/**
 * 默认基于内存的调度器
 */
public class AsyncDispatcher extends AbstractDispatcher {

    private FlowExecutor flowExecutor;

    public AsyncDispatcher() {
        super(new WorkRunnerImpl().startWork());
        flowExecutor = new FlowExecutor();
    }

    @Override
    protected FlowResult doSend(RuntimeContext runtimeContext) {
        super.workRunner.postWork(() -> startFlow(runtimeContext) );
        return new FlowResult().setStatus(FlowStatusEnum.INIT);
    }

    /**
     * 开始执行流程
     * @param runtimeContext
     */
    private void startFlow(RuntimeContext runtimeContext){
        System.out.println("startFlow.......");
        flowExecutor.execute(runtimeContext);
    }
}
