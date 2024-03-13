package net.somta.juggle.console.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import net.somta.juggle.core.enums.*;
import net.somta.juggle.core.model.*;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.model.node.ConditionNode.ConditionExpression;
import net.somta.juggle.core.model.node.EndNode;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.model.node.StartNode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowDefinitionHelper {


    /**
     * mock流程的定义内容
     * @return
     */
    public static String getFlowDefinitionContent() {

        ObjectMapper objectMapper = new ObjectMapper();
        List<FlowElement> elementList = new ArrayList<>();

        //开始节点
        StartNode startEventNode = new StartNode();
        startEventNode.setKey("start_2s49s");
        startEventNode.setName("开始");
        startEventNode.setElementType(ElementTypeEnum.START);
        startEventNode.setOutgoings(Arrays.asList("method_8w9r3"));
        elementList.add(startEventNode);

        //方法节点
        MethodNode methodNode = new MethodNode();
        methodNode.setKey("method_8w9r3");
        methodNode.setName("根据ID获取用户名称");
        methodNode.setDesc("这是了一个节点的描述");
        methodNode.setElementType(ElementTypeEnum.METHOD);

        Method method = new Method();
        method.setDomainId(1L);
        method.setMethodId(1L);
        method.setUrl("http://127.0.0.1:8686/example/user/getUserById");
        method.setRequestType(RequestTypeEnum.GET);
        method.setRequestContentType(RequestContentTypeEnum.APPLICATION_FORM_URLENCODED.getValue());

        //header填充规则
        List<FillStruct> headerFillRules = new ArrayList<>();
        FillStruct headerFillStruct = new FillStruct();
        headerFillStruct.setSource("env_id");
        headerFillStruct.setSourceType(FieldSourceEnum.VARIABLE);
        headerFillStruct.setSourceDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        headerFillStruct.setTarget("userId");
        headerFillStruct.setTargetType(FieldSourceEnum.INPUT_PARAM);
        headerFillStruct.setTargetDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        headerFillRules.add(headerFillStruct);
        method.setHeaderFillRules(headerFillRules);

        //入参填充规则
        List<FillStruct> inputFillRules = new ArrayList<>();
        FillStruct fillStruct = new FillStruct();
        fillStruct.setSource("env_id");
        fillStruct.setSourceType(FieldSourceEnum.VARIABLE);
        fillStruct.setSourceDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        fillStruct.setTarget("userId");
        fillStruct.setTargetType(FieldSourceEnum.INPUT_PARAM);
        fillStruct.setTargetDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        inputFillRules.add(fillStruct);
        method.setInputFillRules(inputFillRules);


        //出参填充规则
        List<FillStruct> outputFillRules = new ArrayList<>();
        FillStruct outFillStruct = new FillStruct();
        outFillStruct.setSource("name");
        outFillStruct.setSourceType(FieldSourceEnum.OUTPUT_PARAM);
        outFillStruct.setSourceDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        outFillStruct.setTarget("env_userName");
        outFillStruct.setTargetType(FieldSourceEnum.VARIABLE);
        outFillStruct.setTargetDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        outputFillRules.add(outFillStruct);
        method.setOutputFillRules(outputFillRules);

        methodNode.setMethod(method);

        methodNode.setIncomings(Arrays.asList("start_2s49s"));
        methodNode.setOutgoings(Arrays.asList("condition_83jd3"));
        elementList.add(methodNode);

        //判断节点
        ConditionNode conditionNode = new ConditionNode();
        conditionNode.setKey("condition_83jd3");
        conditionNode.setName("判断用户名称");
        conditionNode.setElementType(ElementTypeEnum.CONDITION);
        conditionNode.setIncomings(Arrays.asList("method_8w9r3"));
        conditionNode.setOutgoings(Arrays.asList("end_5g463"));

        List<ConditionNode.ConditionItem> conditions = new ArrayList();

        ConditionNode.ConditionItem conditionItem1 = new ConditionNode.ConditionItem();
        conditionItem1.setConditionName("判断用户名称是否为zhansan");
        conditionItem1.setConditionType(ConditionNode.ConditionType.CUSTOM);
        conditionItem1.setExpression("env_name==\"张三\"||string.contains(env_name,三)");

        List<List<ConditionExpression>> conditionExpressions1 = new ArrayList<>();

        //第一个或条件表达式
        List<ConditionExpression> conditionExpressions11 = new ArrayList<>();
        ConditionExpression conditionExpression11 = new ConditionExpression();
        conditionExpression11.setEnvKey("env_name");
        conditionExpression11.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        conditionExpression11.setOperator(OperatorEnum.EQUAL.getCode());
        conditionExpression11.setAssignType("constant");
        conditionExpression11.setValue("张三");
        conditionExpressions11.add(conditionExpression11);
        conditionExpressions1.add(conditionExpressions11);
        //第二个或条件表达式
        List<ConditionExpression> conditionExpressions12 = new ArrayList<>();
        ConditionExpression conditionExpression12 = new ConditionExpression();
        conditionExpression12.setEnvKey("env_name");
        conditionExpression12.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        conditionExpression12.setOperator(OperatorEnum.CONTAINS.getCode());
        conditionExpression12.setAssignType("constant");
        conditionExpression12.setValue("三");
        conditionExpressions12.add(conditionExpression12);
        conditionExpressions1.add(conditionExpressions12);

        conditionItem1.setConditionExpressions(conditionExpressions1);

        conditionItem1.setOutgoing("end_5g463");
        conditions.add(conditionItem1);

        ConditionNode.ConditionItem conditionItem2 = new ConditionNode.ConditionItem();
        conditionItem2.setConditionName("判断用户名称是否为lisi");
        conditionItem2.setConditionType(ConditionNode.ConditionType.CUSTOM);
        //注意：字符串的条件一定要带单引号或者双引号
        conditionItem2.setExpression("env_name==\"lisi\"");

        List<List<ConditionExpression>> conditionExpressions2 = new ArrayList<>();

        //第一个或条件表达式
        List<ConditionExpression> conditionExpressions21 = new ArrayList<>();
        ConditionExpression conditionExpression21 = new ConditionExpression();
        conditionExpression21.setEnvKey("env_name");
        conditionExpression21.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        conditionExpression21.setOperator(OperatorEnum.EQUAL.getCode());
        conditionExpression21.setAssignType("constant");
        conditionExpression21.setValue("lisi");
        conditionExpressions21.add(conditionExpression21);
        conditionExpressions2.add(conditionExpressions21);
        conditionItem2.setConditionExpressions(conditionExpressions2);

        conditionItem2.setOutgoing("method_23s45");
        conditions.add(conditionItem2);

        ConditionNode.ConditionItem conditionItem3 = new ConditionNode.ConditionItem();
        conditionItem3.setConditionName("默认else分支");
        conditionItem3.setConditionType(ConditionNode.ConditionType.DEFAULT);
        conditionItem3.setOutgoing("end_5g463");
        conditions.add(conditionItem3);

        conditionNode.setConditions(conditions);
        elementList.add(conditionNode);

        //方法节点
        MethodNode methodNode2 = new MethodNode();
        methodNode2.setKey("method_23s45");
        methodNode2.setName("获取订单信息");
        methodNode2.setElementType(ElementTypeEnum.METHOD);
        Method method2 = new Method();
        method2.setDomainId(1L);
        method2.setMethodId(2L);
        method2.setUrl("http://127.0.0.1:8686/example/order/queryOrderByNo");
        method2.setRequestType(RequestTypeEnum.GET);
        method.setRequestContentType(RequestContentTypeEnum.APPLICATION_FORM_URLENCODED.getValue());

        //入参填充规则
        /*List<FillStruct> inputFillRules = new ArrayList<>();
        FillStruct fillStruct = new FillStruct();
        fillStruct.setSource("env_id");
        fillStruct.setSourceType(FildSourceEnum.VARIABLE);
        fillStruct.setSourceDataType(new DataType(DataTypeEnum.Integer));
        fillStruct.setTarget("id");
        fillStruct.setTargetType(FildSourceEnum.FLOWINPUT);
        fillStruct.setTargetDataType(new DataType(DataTypeEnum.Integer));
        inputFillRules.add(fillStruct);
        method2.setInputFillRules(inputFillRules);*/

        methodNode2.setMethod(method2);

        methodNode2.setIncomings(Arrays.asList("condition_83jd3"));
        methodNode2.setOutgoings(Arrays.asList("end_5g463"));
        elementList.add(methodNode2);

        //结束节点
        EndNode endEventNode = new EndNode();
        endEventNode.setKey("end_5g463");
        endEventNode.setName("结束");
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

    public static ParameterEntity getFlowDefinitionParameterEntity() {
        ParameterEntity parameterEntity = new ParameterEntity();

        List<OutputParameterVO> outputParameters = new ArrayList<>();
        OutputParameterVO outputParameterVO = new OutputParameterVO();
        outputParameterVO.setParamKey("userName");
        outputParameterVO.setParamName("流程出参-用户名称");
        outputParameterVO.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        outputParameters.add(outputParameterVO);
        parameterEntity.setOutputParameterList(outputParameters);

        return parameterEntity;
    }


    public static List<VariableInfoVO> getFlowDefinitionVariableInfoList() {
        List<VariableInfoVO> variableInfoList = new ArrayList<>();

        VariableInfoVO inputVariable1 = new VariableInfoVO();
        inputVariable1.setEnvKey("env_id");
        inputVariable1.setEnvName("入参-用户ID变量");
        inputVariable1.setEnvType(1);
        inputVariable1.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        variableInfoList.add(inputVariable1);

        VariableInfoVO inputVariable2 = new VariableInfoVO();
        inputVariable2.setEnvKey("env_name");
        inputVariable2.setEnvName("入参-用户姓名变量");
        inputVariable2.setEnvType(1);
        inputVariable2.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        variableInfoList.add(inputVariable2);

        VariableInfoVO outputVariable1 = new VariableInfoVO();
        outputVariable1.setEnvKey("env_userName");
        outputVariable1.setEnvName("流程出参-用户姓名变量");
        outputVariable1.setEnvType(2);
        outputVariable1.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        variableInfoList.add(outputVariable1);

        return variableInfoList;
    }

    public static List<Variable> getFlowRuntimeVariables(List<VariableInfoVO> variableInfoVoList){
        List<Variable> variables = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(variableInfoVoList)){
            for (VariableInfoVO variableInfoVo : variableInfoVoList) {
                Variable variable = new Variable();
                variable.setKey(variableInfoVo.getEnvKey());
                variable.setName(variableInfoVo.getEnvName());
                variable.setDataType(JsonSerializeHelper.deserialize(variableInfoVo.getDataType(), DataType.class));
                variables.add(variable);
            }
        }
        return variables;
    }


    public static void main(String[] args) {
        String flowContent = getFlowDefinitionContent();
        System.out.println(flowContent);

        /*ObjectMapper objectMapper = new ObjectMapper();
        List<FlowElement> nodeList = new ArrayList<>();
        try {
            nodeList = objectMapper.readValue(flowContent, new TypeReference<List<FlowElement>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(nodeList.size());*/

    }
}
