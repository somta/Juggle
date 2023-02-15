package net.somta.juggle.core.executor;

import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.enums.FlowStatusEnum;

/**
 * 流程执行器,整个流程从这里开始执行，然后让每个执行器去执行
 *
 * @author husong
 * @date 2023/02/04
 */
public class FlowExecutor implements IExecutor{
    @Override
    public void execute(RuntimeContext runtimeContext) {
        FlowStatusEnum processStatus = FlowStatusEnum.FINISH;
        try {
            preExecute(runtimeContext);
            doExecute(runtimeContext);
        } catch (Exception e) {
            System.out.println("流程执行异常");
            processStatus = FlowStatusEnum.ABORT;
            e.printStackTrace();
        } finally {
            runtimeContext.setFlowStatus(processStatus);
            postExecute(runtimeContext);
        }
    }

    @Override
    public IExecutor getExecutor(RuntimeContext runtimeContext) {
        //1.判断上一个节点是否执行完成了

        //2.返回当前节点的执行器
        return ExecutorFactory.getElementExecutor(runtimeContext.getCurrentNode());
    }

    /**
     * 整个流程之前需要执行的前置执行器
     * @param runtimeContext
     */
    private void preExecute(RuntimeContext runtimeContext) {
        System.out.println("开始执行执行流程前的逻辑... . . .");
    }

    /**
     * 执行具体每个流程
     * @param runtimeContext
     */
    private void doExecute(RuntimeContext runtimeContext) {
        System.out.println("执行流程中......");
        IExecutor runtimeExecutor = getExecutor(runtimeContext);
        while (runtimeExecutor != null) {
            runtimeExecutor.execute(runtimeContext);
            runtimeExecutor = runtimeExecutor.getExecutor(runtimeContext);
        }
    }

    /**
     * 整个流程执行后需要执行的后置执行器
     * @param runtimeContext
     */
    private void postExecute(RuntimeContext runtimeContext) {
        System.out.println("执行流程全部完成......开始组装结果");
    }


}
