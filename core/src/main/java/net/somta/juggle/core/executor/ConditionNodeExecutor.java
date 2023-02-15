package net.somta.juggle.core.executor;

import net.somta.juggle.core.RuntimeContext;

/**
 * 判断节点执行器
 *
 * @author husong
 * @date 2023/02/06
 */
public class ConditionNodeExecutor extends ElementExecutor{

    @Override
    protected void doPreExecute(RuntimeContext runtimeContext) {
        System.out.println("判断节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(RuntimeContext runtimeContext) {
        System.out.println("判断节点执行器，执行中。。。");
    }

    @Override
    protected void doPostExecute(RuntimeContext runtimeContext) {
        System.out.println("判断节点执行器，执行后========================================");
    }
}
