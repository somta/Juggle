package net.somta.juggle.core.dispatcher.impl;

import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.WorkRunnerImpl;
import net.somta.juggle.core.dispatcher.AbstractDispatcher;
import net.somta.juggle.core.executor.FlowExecutor;

/**
 * 默认基于内存的调度器
 */
public class DefaultDispatcher extends AbstractDispatcher {

    private FlowExecutor flowExecutor;

    public DefaultDispatcher() {
        super(new WorkRunnerImpl().startWork());
        flowExecutor = new FlowExecutor();
    }

    @Override
    protected Boolean doSend(RuntimeContext runtimeContext) {
        super.workRunner.postWork(() -> startFlow(runtimeContext) );
        return true;
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
