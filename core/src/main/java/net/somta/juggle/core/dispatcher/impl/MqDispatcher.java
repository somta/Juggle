package net.somta.juggle.core.dispatcher.impl;

import net.somta.juggle.core.IWorkRunner;
import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.WorkRunnerImpl;
import net.somta.juggle.core.dispatcher.AbstractDispatcher;

/**
 * todo 应该在分发器中
 * 基于MQ的分发器,
 */
public class MqDispatcher extends AbstractDispatcher {

    public MqDispatcher() {
        super(new WorkRunnerImpl().startWork());
    }

    @Override
    protected Boolean doSend(RuntimeContext runtimeContext) {

        return null;
    }
}
