package net.somta.juggle.core.executor;

import net.somta.juggle.core.RuntimeContext;

/**
 * 开始节点的执行器
 *
 * @author husong
 * @date 2023/02/06
 */
public class StartNodeExecutor extends ElementExecutor{

    @Override
    protected void doExecute(RuntimeContext runtimeContext) {
        System.out.println("开始节点执行器，执行中。。。");
    }

    @Override
    protected void doPostExecute(RuntimeContext runtimeContext) {

    }
}
