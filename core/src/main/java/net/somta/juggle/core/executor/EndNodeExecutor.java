package net.somta.juggle.core.executor;

import net.somta.juggle.core.FlowRuntimeContext;

/**
 * 开始节点的执行器
 *
 * @author husong
 * @date 2023/02/06
 */
public class EndNodeExecutor extends AbstractElementExecutor {

    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("结束节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("结束节点执行器，执行中。。。");
    }

    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("结束节点执行器，执行后========================================");
    }
}
