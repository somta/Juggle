package net.somta.juggle.core.executor;

import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.OutputParameter;
import net.somta.juggle.core.result.IFlowResultManager;
import net.somta.juggle.core.variable.AbstractVariableManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final static Logger logger = LoggerFactory.getLogger(FlowExecutor.class);


    public Map<String,Object> execute(FlowRuntimeContext flowRuntimeContext) {
        Map<String,Object> flowResult = null;
        FlowStatusEnum processStatus = FlowStatusEnum.FINISH;
        try {
            preExecute(flowRuntimeContext);
            doExecute(flowRuntimeContext);
        } catch (Exception e) {
            logger.error("流程执行异常:{}",e.getMessage());
            processStatus = FlowStatusEnum.ABORT;
            e.printStackTrace();
        } finally {
            flowRuntimeContext.setFlowStatus(processStatus);
            flowResult = postExecute(flowRuntimeContext);
        }
        return flowResult;
    }

    public IExecutor getExecutor(FlowRuntimeContext flowRuntimeContext) {
        //1.判断上一个节点是否执行完成了

        //2.返回当前节点的执行器
        return ExecutorFactory.getElementExecutor(flowRuntimeContext.getCurrentNode());
    }

    /**
     * 整个流程之前需要执行的前置执行器
     * @param flowRuntimeContext
     */
    private void preExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("开始执行执行流程前的逻辑... . . .");
    }

    /**
     * 执行具体每个流程
     * @param flowRuntimeContext
     */
    private void doExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("执行流程中......");
        IExecutor runtimeExecutor = getExecutor(flowRuntimeContext);
        while (runtimeExecutor != null) {
            runtimeExecutor.execute(flowRuntimeContext);
            runtimeExecutor = runtimeExecutor.getExecutor(flowRuntimeContext);
        }
    }

    /**
     * 整个流程执行后需要执行的后置执行器
     * @param flowRuntimeContext
     */
    private Map<String,Object> postExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("执行流程全部完成......开始组装结果");
        List<OutputParameter> outputParameters = flowRuntimeContext.getOutputParameters();
        AbstractVariableManager variableManager = flowRuntimeContext.getVariableManager();
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
        IFlowResultManager flowResultManager = flowRuntimeContext.getFlowResultManager();
        flowResultManager.putFlowResult(flowRuntimeContext.getFlowInstanceId(),result);
        return result;
    }


}
