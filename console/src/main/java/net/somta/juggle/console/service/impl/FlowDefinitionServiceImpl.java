package net.somta.juggle.console.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.console.service.IFlowDefinitionService;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.model.node.EndNode;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.model.node.StartNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FlowDefinitionServiceImpl implements IFlowDefinitionService {
    @Override
    public FlowDefinition getFlowDefinitionByKey(String flowKey) {
        //TODO 先mock一个流程定义数据
        FlowDefinition workflowDefinition = new FlowDefinition();
        workflowDefinition.setFlowKey("flow_123");
        workflowDefinition.setTenantId("66");
        workflowDefinition.setRemark("这是一个测试流程");
        workflowDefinition.setContent(getFlowDefinitionContent());
        return workflowDefinition;
    }


    /**
     * 获取流程的定义内容，先mock的数据,原本应该从数据库取
     * @return
     */
    private String getFlowDefinitionContent() {

        ObjectMapper objectMapper = new ObjectMapper();
        List<FlowElement> elementList = new ArrayList<>();

        //开始节点
        StartNode startEventNode = new StartNode();
        startEventNode.setKey("start_2s49s");
        startEventNode.setElementType(ElementTypeEnum.START);
        startEventNode.setOutgoings(Arrays.asList("method_8w9r3"));
        elementList.add(startEventNode);

        //方法节点
        MethodNode methodNode = new MethodNode();
        methodNode.setKey("method_8w9r3");
        methodNode.setElementType(ElementTypeEnum.METHOD);
        methodNode.setUrl("http://baidu.com");
        methodNode.setRequestType("GET");
        methodNode.setIncomings(Arrays.asList("start_2s49s"));
        methodNode.setOutgoings(Arrays.asList("condition_83jd3"));
        elementList.add(methodNode);

        //判断节点
        ConditionNode conditionNode = new ConditionNode();
        conditionNode.setKey("condition_83jd3");
        conditionNode.setElementType(ElementTypeEnum.CONDITION);
        methodNode.setIncomings(Arrays.asList("method_8w9r3"));
        methodNode.setOutgoings(Arrays.asList("end_5g463"));
        elementList.add(conditionNode);

        //结束节点
        EndNode endEventNode = new EndNode();
        endEventNode.setKey("end_5g463");
        endEventNode.setElementType(ElementTypeEnum.END);
        endEventNode.setIncomings(Arrays.asList("condition_83jd3"));
        elementList.add(endEventNode);
        String content = null;
        try {
            content = objectMapper.writeValueAsString(elementList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return content;
    }
}
