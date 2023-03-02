package net.somta.juggle.console.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.enums.FildSourceEnum;
import net.somta.juggle.core.enums.RequestTypeEnum;
import net.somta.juggle.core.model.*;
import net.somta.juggle.console.service.IFlowDefinitionService;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.model.node.EndNode;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.model.node.StartNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
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
        workflowDefinition.setOutputParameters(mockOutputParameters());
        workflowDefinition.setContent(getFlowDefinitionContent());
        return workflowDefinition;
    }

    private List<OutputParameter> mockOutputParameters(){
        List<OutputParameter>  outputParameters = new ArrayList<>();
        OutputParameter userNameParm = new OutputParameter();
        userNameParm.setKey("userName");
        userNameParm.setName("流程出参-用户名称");
        userNameParm.setDataType(new DataTypeInfo(DataTypeEnum.String));
        outputParameters.add(userNameParm);
        return outputParameters;
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

        Method method = new Method();
        method.setUrl("http://127.0.0.1:8080/test/info");
        method.setRequestType(RequestTypeEnum.GET);
        //入参设置
        List<InputParameter> inputParameters = new ArrayList<>();
        InputParameter idParm = new InputParameter();
        idParm.setKey("id");
        idParm.setName("用户ID");
        idParm.setRequired(true);
        idParm.setDataType(new DataTypeInfo(DataTypeEnum.Integer));
        idParm.setDefaultValue("666");
        inputParameters.add(idParm);
        method.setInputParameters(inputParameters);

        //入参填充规则
        List<FillStruct> inputFillRules = new ArrayList<>();
        FillStruct fillStruct = new FillStruct();
        fillStruct.setSource("env_id");
        fillStruct.setSourceType(FildSourceEnum.VARIABLE);
        fillStruct.setSourceDataType(new DataTypeInfo(DataTypeEnum.Integer));
        fillStruct.setTarget("id");
        fillStruct.setTargetType(FildSourceEnum.FLOWINPUT);
        fillStruct.setTargetDataType(new DataTypeInfo(DataTypeEnum.Integer));
        inputFillRules.add(fillStruct);
        method.setInputFillRules(inputFillRules);

        //出参设置
        List<OutputParameter> outputParameters = new ArrayList<>();
        OutputParameter nameParm = new OutputParameter();
        nameParm.setKey("name");
        nameParm.setName("用户名称");
        nameParm.setDataType(new DataTypeInfo(DataTypeEnum.String));
        outputParameters.add(nameParm);
        method.setOutputParameters(outputParameters);

        //出参填充规则
        List<FillStruct> outputFillRules = new ArrayList<>();
        FillStruct outFillStruct = new FillStruct();
        outFillStruct.setSource("name");
        outFillStruct.setSourceType(FildSourceEnum.OUTPUTPARAM);
        outFillStruct.setSourceDataType(new DataTypeInfo(DataTypeEnum.String));
        outFillStruct.setTarget("env_userName");
        outFillStruct.setTargetType(FildSourceEnum.VARIABLE);
        outFillStruct.setTargetDataType(new DataTypeInfo(DataTypeEnum.String));
        outputFillRules.add(outFillStruct);
        method.setOutputFillRules(outputFillRules);

        methodNode.setMethod(method);

        methodNode.setIncomings(Arrays.asList("start_2s49s"));
        methodNode.setOutgoings(Arrays.asList("condition_83jd3"));
        elementList.add(methodNode);

        //判断节点
        ConditionNode conditionNode = new ConditionNode();
        conditionNode.setKey("condition_83jd3");
        conditionNode.setElementType(ElementTypeEnum.CONDITION);
        conditionNode.setIncomings(Arrays.asList("method_8w9r3"));
        conditionNode.setOutgoings(Arrays.asList("end_5g463"));

        LinkedHashMap<String,String> conditions = new LinkedHashMap<>();
        conditions.put("end_5g463","env_name=zhansan");

        conditionNode.setConditions(conditions);

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
