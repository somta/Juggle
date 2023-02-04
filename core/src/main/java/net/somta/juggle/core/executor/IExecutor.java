package net.somta.juggle.core.executor;

import net.somta.juggle.core.RuntimeContext;

public interface IExecutor {

    /**
     * 执行流程
     * @param runtimeContext
     */
    void execute(RuntimeContext runtimeContext);
}
