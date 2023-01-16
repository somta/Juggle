package net.somta.juggle.core.dispatcher.impl;

import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.WorkRunnerImpl;
import net.somta.juggle.core.dispatcher.AbstractDispatcher;

/**
 * 默认基于内存的调度器
 */
public class DefaultDispatcher extends AbstractDispatcher {

    public DefaultDispatcher() {
        super(new WorkRunnerImpl().startWork());
    }

    @Override
    protected Boolean doSend(RuntimeContext runtimeContext) {
        //super.workRunner.postWork();
        return null;
    }
}
