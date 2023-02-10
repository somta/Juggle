package net.somta.juggle.core.executor;

import net.somta.juggle.core.RuntimeContext;

/**
 * TODO 类职责描述
 *
 * @author husong
 * @date 2023/02/06
 */
public class MethodNodeExecutor extends ElementExecutor{

    @Override
    protected void doPreExecute(RuntimeContext runtimeContext) {
        System.out.println("方法节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(RuntimeContext runtimeContext) {
        System.out.println("方法节点执行器，执行中。。。");
    }

    @Override
    protected void doPostExecute(RuntimeContext runtimeContext) {
        System.out.println("方法节点执行器，执行后。。。");
    }
}
