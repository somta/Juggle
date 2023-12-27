package net.somta.juggle.core.dispatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.juggle.core.IWorkRunner;
import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.FlowResult;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.result.IFlowResultManager;
import net.somta.juggle.core.variable.AbstractVariableManager;
import org.apache.commons.collections4.MapUtils;

import java.util.*;
import java.util.stream.Collectors;

import static net.somta.juggle.core.enums.ErrorEnum.FLOW_ELEMENT_IS_EMPTY_ERROR;

/**
 * @author husong
 */
public abstract class AbstractDispatcher implements IDispatcher {

    /**
     * 执行work
     */
    protected IWorkRunner workRunner;

    public AbstractDispatcher(IWorkRunner workRunner) {
        this.workRunner = workRunner;
    }

    @Override
    public FlowResult doDispatcher(Flow flow, Map<String,Object> flowData, IFlowResultManager flowResultManager) {
        //1.校验流程正确性

        //2.构建流程运行的RuntimeContext
        FlowRuntimeContext flowRuntimeContext = buildRuntimeContext(flow);
        flowRuntimeContext.setFlowResultManager(flowResultManager);

        try {
            //3.将提交的入参的数据和入参的变量对应上，并将值设置到变量上，入参值后面要用
            fillInputParameterVariable(flowRuntimeContext.getVariableManager(),flowData);
        } catch (FlowException e) {
            e.printStackTrace();
        }
        return doSend(flowRuntimeContext);
    }

    /**
     * 填充入参变量的值
     * @param variableManager
     * @param flowData
     */
    private void fillInputParameterVariable(AbstractVariableManager variableManager, Map<String,Object> flowData) throws FlowException {
        if(MapUtils.isNotEmpty(flowData)){
            for (String key : flowData.keySet()) {
                variableManager.setVariableValue("env_"+key,flowData.get(key));
            }
        }
    }

    /**
     * 构建流程运行的RuntimeContext
     * @return
     */
    private FlowRuntimeContext buildRuntimeContext(Flow flow){

        Map<String,Variable> variableSchemaMap = flow.getVariables().stream().collect(Collectors.toMap(Variable::getKey, account -> account));

        FlowRuntimeContext flowRuntimeContext = new FlowRuntimeContext(variableSchemaMap);
        flowRuntimeContext.setFlowInstanceId(flow.getFlowInstanceId());
        flowRuntimeContext.setFlowStatus(FlowStatusEnum.INIT);
        flowRuntimeContext.setFlowKey(flow.getFlowKey());
        flowRuntimeContext.setTenantId(flow.getTenantId());
        flowRuntimeContext.setOutputParameters(flow.getOutputParams());
        Map<String, FlowElement> flowElementMap = buildFlowElementMap(flow.getFlowContent());
        if(MapUtils.isEmpty(flowElementMap)){
            throw new FlowException(FLOW_ELEMENT_IS_EMPTY_ERROR);
        }
        flowRuntimeContext.setFlowElementMap(flowElementMap);
        flowRuntimeContext.setCurrentNode(getFirstElement(flowElementMap));
        return flowRuntimeContext;
    }

    /**
     *
     * 构建流程元素Map
     * @param content
     * @return
     */
    private Map<String, FlowElement> buildFlowElementMap(String content){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<FlowElement> elementList = objectMapper.readValue(content, new TypeReference<List<FlowElement>>() {});
            Map<String, FlowElement> flowElementMap = elementList.stream().collect(Collectors.toMap(FlowElement::getKey,e -> e));
            return flowElementMap;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_MAP;
    }

    /**
     * 获取开始节点
     * @return
     */
    private FlowElement getFirstElement(Map<String, FlowElement> flowElementMap){
        for (FlowElement flowElement : flowElementMap.values()) {
            if(flowElement.getElementType() == ElementTypeEnum.START){
                return flowElement;
            }
        }
        return null;
    }

    protected abstract FlowResult doSend(FlowRuntimeContext flowRuntimeContext);
}
