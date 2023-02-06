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
    public void execute(RuntimeContext runtimeContext) {
        try {
            //preExecute(runtimeContext);
            doExecute(runtimeContext);
        } catch (Exception ex) {
            System.out.println("执行流程出现异常了。。。。");
        } finally {
            doPostExecute(runtimeContext);
        }
    }

    @Override
    public IExecutor getExecutor(RuntimeContext runtimeContext) {
        return null;
    }

    protected abstract void doExecute(RuntimeContext runtimeContext);

    protected abstract void doPostExecute(RuntimeContext runtimeContext);
}
