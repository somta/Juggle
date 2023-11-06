package net.somta.juggle.core.executor;

import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.OutputParameter;
import net.somta.juggle.core.result.IFlowResultManager;
import net.somta.juggle.core.variable.BaseVariableManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程执行器,整个流程从这里开始执行，然后让每个执行器去执行
 *
 * @author husong
 * @date 2023/02/04
 */
public class FlowExecutor{


    public Map<String,Object> execute(RuntimeContext runtimeContext) {
        Map<String,Object> flowResult = null;
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
            flowResult = postExecute(runtimeContext);
        }
        return flowResult;
    }

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
    private Map<String,Object> postExecute(RuntimeContext runtimeContext) {
        System.out.println("执行流程全部完成......开始组装结果");
        List<OutputParameter> outputParameters = runtimeContext.getOutputParameters();
        BaseVariableManager variableManager = runtimeContext.getVariableManager();
        Map<String,Object> result = new HashMap<>(16);
        for (OutputParameter parameter : outputParameters) {
            Object value = null;
            try {
                value = variableManager.getVariableValue("env_"+parameter.getKey());
            } catch (FlowException e) {
                e.printStackTrace();
            }
            result.put(parameter.getKey(),value);
        }
        System.out.println("流程执行结果为："+result);
        IFlowResultManager flowResultManager = runtimeContext.getFlowResultManager();
        flowResultManager.putFlowResult(runtimeContext.getFlowInstanceId(),result);
        return result;
    }


}
