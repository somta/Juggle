package net.somta.juggle.core.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.collections4.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.somta.juggle.core.enums.CoreErrorEnum.FLOW_ELEMENT_IS_EMPTY_ERROR;
import static net.somta.juggle.core.enums.CoreErrorEnum.FLOW_NOT_EXIST_START_NODE_ERROR;

public class FlowHelper {


    public static FlowRuntimeContext buildFlowRuntimeContext(){
        Flow flow = getDefaultFlow();
        Map<String, Variable> variableSchemaMap = flow.getVariables().stream().collect(Collectors.toMap(Variable::getKey, account -> account));

        FlowRuntimeContext flowRuntimeContext = new FlowRuntimeContext(variableSchemaMap);
        flowRuntimeContext.setFlowInstanceId(flow.getFlowInstanceId());
        flowRuntimeContext.setFlowStatus(FlowStatusEnum.INIT);
        flowRuntimeContext.setFlowKey(flow.getFlowKey());
        flowRuntimeContext.setOutputParameters(flow.getOutputParams());
        Map<String, FlowElement> flowElementMap = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<FlowElement> elementList = objectMapper.readValue(flow.getFlowContent(), new TypeReference<List<FlowElement>>() {});
            flowElementMap = elementList.stream().collect(Collectors.toMap(FlowElement::getKey,e -> e));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(MapUtils.isEmpty(flowElementMap)){
            throw new FlowException(FLOW_ELEMENT_IS_EMPTY_ERROR);
        }
        flowRuntimeContext.setFlowElementMap(flowElementMap);

        for (FlowElement flowElement : flowElementMap.values()) {
            if(flowElement.getElementType() == ElementTypeEnum.START){
                flowRuntimeContext.setCurrentNode(flowElement);
            }
        }
        if(flowRuntimeContext.getCurrentNode() == null){
            throw new FlowException(FLOW_NOT_EXIST_START_NODE_ERROR);
        }
        return flowRuntimeContext;
    }

    public static Flow getDefaultFlow(){
        Flow flow = new Flow();
        flow.setFlowKey("unitFlow");
        List<Variable> variables = new ArrayList<>();
        flow.setVariables(variables);
        return flow;
    }

}
