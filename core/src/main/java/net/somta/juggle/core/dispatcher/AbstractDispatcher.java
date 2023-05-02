package net.somta.juggle.core.dispatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.juggle.core.IWorkRunner;
import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.core.variable.VariableManager;
import org.apache.commons.collections.MapUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractDispatcher implements IDispatcher {

    /**
     * 执行work
     */
    protected IWorkRunner workRunner;

    public AbstractDispatcher(IWorkRunner workRunner) {
        System.out.println("AbstractDispatcher init.....");
        this.workRunner = workRunner;
    }

    @Override
    public Boolean doDispatcher(Flow flow, Map<String,Object> flowData) {
        //1.校验流程正确性

        //2.构建流程运行的RuntimeContext
        RuntimeContext runtimeContext = buildRuntimeContext(flow);

        try {
            //3.将提交的入参的数据和入参的变量对应上，并将值设置到变量上，入参值后面要用
            fillInputParameterVariable(runtimeContext.getVariableManager(),flowData);
        } catch (FlowException e) {
            e.printStackTrace();
        }
        return doSend(runtimeContext);
    }

    /**
     * 填充入参变量的值
     * @param variableManager
     * @param flowData
     */
    private void fillInputParameterVariable(VariableManager variableManager, Map<String,Object> flowData) throws FlowException {
        if(!CollectionUtils.isEmpty(flowData)){
            for (String key : flowData.keySet()) {
                variableManager.setVariableValue("env_"+key,flowData.get(key));
            }
        }
    }

    /**
     * 构建流程运行的RuntimeContext
     * @return
     */
    private RuntimeContext buildRuntimeContext(Flow flow){

        Map<String,Variable> variableSchemaMap = flow.getVariables().stream().collect(Collectors.toMap(Variable::getKey, account -> account));

        RuntimeContext runtimeContext = new RuntimeContext(variableSchemaMap);
        runtimeContext.setFlowStatus(FlowStatusEnum.INIT);
        runtimeContext.setFlowKey(flow.getFlowKey());
        runtimeContext.setTenantId(flow.getTenantId());
        runtimeContext.setOutputParameters(flow.getOutputParams());
        Map<String, FlowElement> flowElementMap = buildFlowElementMap(flow.getFlowContent());
        if(MapUtils.isEmpty(flowElementMap)){
            System.out.println("流程元素错误了，直接报错");
        }
        runtimeContext.setFlowElementMap(flowElementMap);
        runtimeContext.setCurrentNode(getFirstElement(flowElementMap));
        return runtimeContext;
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
        return MapUtils.EMPTY_MAP;
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

    protected abstract Boolean doSend(RuntimeContext runtimeContext);
}
