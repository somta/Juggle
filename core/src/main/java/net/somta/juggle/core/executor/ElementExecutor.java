package net.somta.juggle.core.executor;

import net.somta.juggle.core.RuntimeContext;

/**
 * 抽象的元素节点执行器
 *
 * @author husong
 * @date 2023/02/06
 */
public abstract class ElementExecutor implements IExecutor {

    @Override
    public IExecutor getExecutor(RuntimeContext runtimeContext) {
        return null;
    }
}
