package net.somta.juggle.core.dispatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.juggle.core.IWorkRunner;
import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.model.FlowDefinition;

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
    public Boolean send(FlowDefinition flowDefinition, List<Variable> variables) {
        //1.校验流程正确性

        //2.构建流程运行的RuntimeContext
        RuntimeContext runtimeContext = buildRuntimeContext(flowDefinition);


        return doSend(runtimeContext);
    }

    /**
     * 构建流程运行的RuntimeContext
     * @return
     */
    private RuntimeContext buildRuntimeContext(FlowDefinition flowDefinition){

        RuntimeContext runtimeContext = new RuntimeContext();
        runtimeContext.setFlowStatus(FlowStatusEnum.INIT);
        runtimeContext.setFlowKey(flowDefinition.getFlowKey());
        runtimeContext.setTenantId(flowDefinition.getTenantId());
        Map<String, FlowElement> flowElementMap = buildFlowElementMap(flowDefinition.getContent());
        if(flowElementMap == null){
            System.out.println("流程元素错误了，直接报错");
        }
        runtimeContext.setFlowMap(flowElementMap);
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
        return null;
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
