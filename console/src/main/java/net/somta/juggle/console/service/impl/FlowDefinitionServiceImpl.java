package net.somta.juggle.console.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.enums.ParameterTypeEnum;
import net.somta.juggle.console.mapper.*;
import net.somta.juggle.console.model.*;
import net.somta.juggle.console.model.param.FlowDefinitionPageParam;
import net.somta.juggle.console.model.param.FlowDefinitionParam;
import net.somta.juggle.console.model.param.InputParameterParam;
import net.somta.juggle.console.model.param.OutputParameterParam;
import net.somta.juggle.console.model.vo.ParameterVO;
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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlowDefinitionServiceImpl extends BaseServiceImpl<FlowDefinitionInfo> implements IFlowDefinitionService {

    @Autowired
    private FlowDefinitionMapper flowDefinitionMapper;
    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private ParameterMapper parameterMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VariableInfoMapper variableInfoMapper;

    @Override
    public IBaseMapper getMapper() {
        return flowDefinitionMapper;
    }

    @Transactional
    @Override
    public Boolean addFlowDefinition(FlowDefinitionParam flowDefinitionParam) {
        FlowDefinitionInfo flowDefinition = new FlowDefinitionInfo();
        String flowKey = RandomStringUtils.random(10, true, true);
        flowDefinition.setFlowKey(flowKey);
        flowDefinition.setFlowName(flowDefinitionParam.getFlowName());
        flowDefinitionMapper.addFlowDefinitionInfo(flowDefinition);
        saveParametersAndVariables(flowDefinition.getId(),flowDefinitionParam.getInputs(), flowDefinitionParam.getOutputs());
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteFlowDefinition(Long flowDefinitionId) {
        flowDefinitionMapper.deleteById(flowDefinitionId);
        ParameterVO parameterVO = new ParameterVO();
        parameterVO.setSourceType(ParameterSourceTypeEnum.FLOW.getCode());
        parameterVO.setSourceId(flowDefinitionId);
        parameterMapper.deleteParameter(parameterVO);
        return true;
    }

    @Transactional
    @Override
    public Boolean updateFlowDefinition(FlowDefinitionParam flowDefinitionParam) {
        FlowDefinitionInfo flowDefinitionInfo = new FlowDefinitionInfo();
        flowDefinitionInfo.setId(flowDefinitionParam.getId());
        flowDefinitionInfo.setFlowName(flowDefinitionParam.getFlowName());
        // todo  这里逻辑不对
        flowDefinitionMapper.update(flowDefinitionInfo);
        ParameterVO parameterVO = new ParameterVO();
        parameterVO.setSourceType(ParameterSourceTypeEnum.FLOW.getCode());
        parameterVO.setSourceId(flowDefinitionParam.getId());
        parameterMapper.deleteParameter(parameterVO);
        variableInfoMapper.deleteVariableByFlowDefinitionId(flowDefinitionParam.getId());
        saveParametersAndVariables(flowDefinitionParam.getId(),flowDefinitionParam.getInputs(), flowDefinitionParam.getOutputs());
        return true;
    }

    @Override
    public FlowDefinitionInfo getFlowDefinitionById(Long flowDefinitionId) {
        return flowDefinitionMapper.queryById(flowDefinitionId);
    }

    @Override
    public List<FlowDefinitionInfo> getFlowDefinitionList(FlowDefinitionPageParam flowDefinitionPageParam) {
        List<FlowDefinitionInfo> list = flowDefinitionMapper.queryByList(flowDefinitionPageParam);
        return list;
    }


    // todo 在这里转成流程执行运行需要的对象，而不是在触发流程哪里转
    @Transactional
    @Override
    public Boolean deployFlowDefinition(FlowDefinitionInfo flowDefinitionInfo,String version) {
        FlowInfo flowInfo = new FlowInfo();
        flowInfo.setFlowKey(flowDefinitionInfo.getFlowKey());
        flowInfo.setFlowName(flowDefinitionInfo.getFlowName());
        flowInfo.setTenantId(flowDefinitionInfo.getTenantId());
        flowInfo.setRemark(flowDefinitionInfo.getRemark());

        //处理出入参数
        ParameterVO parameterQueryVO = new ParameterVO();
        parameterQueryVO.setSourceType(ParameterSourceTypeEnum.FLOW.getCode());
        parameterQueryVO.setSourceId(flowDefinitionInfo.getId());
        List<Parameter> parameterList = parameterMapper.getParameterListByVO(parameterQueryVO);
        if(CollectionUtils.isNotEmpty(parameterList)){
            // 处理入参
            List<Parameter> inputParameterList = parameterList.stream()
                    .filter(parameter -> ParameterTypeEnum.INPUT_PARAM.getCode() == parameter.getParamType()).collect(Collectors.toList());
            if(CollectionUtils.isNotEmpty(inputParameterList)){
                List<InputParameter> inputParams = new ArrayList<>();
                for (Parameter parameter : inputParameterList) {
                    InputParameter inputParameter = new InputParameter();
                    inputParameter.setKey(parameter.getParamKey());
                    inputParameter.setName(parameter.getParamName());
                    inputParameter.setDataType(JsonSerializeHelper.deserialize(parameter.getDataType(),DataType.class));
                    inputParameter.setRequired(parameter.getRequired());
                    inputParams.add(inputParameter);
                }
                String inputParameterString = null;
                try {
                    inputParameterString = objectMapper.writeValueAsString(inputParams);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                flowInfo.setInputs(inputParameterString);
            }

            //处理出参
            List<Parameter> outputParameterList = parameterList.stream()
                    .filter(parameter -> ParameterTypeEnum.OUTPUT_PARAM.getCode() == parameter.getParamType()).collect(Collectors.toList());
            if(CollectionUtils.isNotEmpty(outputParameterList)){
                List<OutputParameter> outputParams = new ArrayList<>();
                for (Parameter parameter : outputParameterList) {
                    OutputParameter outputParameter = new OutputParameter();
                    outputParameter.setKey(parameter.getParamKey());
                    outputParameter.setName(parameter.getParamName());
                    outputParameter.setDataType(JsonSerializeHelper.deserialize(parameter.getDataType(),DataType.class));
                    outputParams.add(outputParameter);
                }
                try {
                    String outputParameterString = objectMapper.writeValueAsString(outputParameterList);
                    flowInfo.setOutputs(outputParameterString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        }

        //处理变量
        List<VariableInfo> variableInfoList = variableInfoMapper.queryVariableInfoListByDefinitionId(flowDefinitionInfo.getId());
        if(CollectionUtils.isNotEmpty(variableInfoList)){
            List<Variable> variables = new ArrayList<>();
            String variablesString = null;
            for (VariableInfo variableInfo : variableInfoList) {
                Variable variable = new Variable();
                variable.setKey(variableInfo.getEnvKey());
                variable.setName(variableInfo.getEnvName());
                variable.setDataType(JsonSerializeHelper.deserialize(variableInfo.getDataType(),DataType.class));
                variables.add(variable);
            }
            try {
                variablesString = objectMapper.writeValueAsString(variables);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            flowInfo.setVariables(variablesString);
        }
        flowMapper.addFlow(flowInfo);
        return true;
    }

    @Override
    public FlowDefinition getFlowDefinitionByKey(String flowKey) {
        //TODO 先mock一个流程定义数据
        FlowDefinition workflowDefinition = new FlowDefinition();
        workflowDefinition.setFlowKey("flow_123");
        workflowDefinition.setTenantId("66");
        workflowDefinition.setRemark("这是一个测试流程");
        workflowDefinition.setOutputParameters(mockOutputParameters());
        workflowDefinition.setContent(getFlowDefinitionContent());
        System.out.println(getFlowDefinitionContent());
        return workflowDefinition;
    }

    private List<OutputParameter> mockOutputParameters(){
        List<OutputParameter>  outputParameters = new ArrayList<>();
        OutputParameter userNameParm = new OutputParameter();
        userNameParm.setKey("userName");
        userNameParm.setName("流程出参-用户名称");
        userNameParm.setDataType(new DataType(DataTypeEnum.String));
        outputParameters.add(userNameParm);
        return outputParameters;
    }

    /**
     * todo 这里还要做保存变量的事情
     * 保存参数
     * @param flowDefinitionId
     * @param inputParameterList
     * @param outputParameterList
     */
    private void saveParametersAndVariables(Long flowDefinitionId,List<InputParameterParam> inputParameterList,
                                List<OutputParameterParam> outputParameterList){
        List<Parameter> parameters = new ArrayList<>();
        List<VariableInfo> variableInfos = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(inputParameterList)){
            for (InputParameterParam inputParameterParam : inputParameterList) {
                Parameter parameter = new Parameter();
                parameter.setParamType(ParameterTypeEnum.INPUT_PARAM.getCode());
                parameter.setParamName(inputParameterParam.getParamName());
                parameter.setDataType(inputParameterParam.getDataType());
                parameter.setRequired(inputParameterParam.getRequired());
                parameter.setSourceType(ParameterSourceTypeEnum.FLOW.getCode());
                parameter.setSourceId(flowDefinitionId);
                parameters.add(parameter);

                VariableInfo variableInfo = new VariableInfo();
                variableInfo.setFlowDefinitionId(flowDefinitionId);
                variableInfo.setEnvKey(inputParameterParam.getParamKey());
                variableInfo.setEnvName(inputParameterParam.getParamName());
                variableInfo.setEnvType(1);
                variableInfo.setDataType(inputParameterParam.getDataType());
                variableInfos.add(variableInfo);
            }
        }
        if(CollectionUtils.isNotEmpty(outputParameterList)){
            for (OutputParameterParam outputParameterParam : outputParameterList) {
                Parameter parameter = new Parameter();
                parameter.setParamType(ParameterTypeEnum.OUTPUT_PARAM.getCode());
                parameter.setParamName(outputParameterParam.getParamName());
                parameter.setDataType(outputParameterParam.getDataType());
                parameter.setSourceType(ParameterSourceTypeEnum.FLOW.getCode());
                parameter.setSourceId(flowDefinitionId);
                parameters.add(parameter);

                VariableInfo variableInfo = new VariableInfo();
                variableInfo.setFlowDefinitionId(flowDefinitionId);
                variableInfo.setEnvKey(outputParameterParam.getParamKey());
                variableInfo.setEnvName(outputParameterParam.getParamName());
                variableInfo.setEnvType(2);
                variableInfo.setDataType(outputParameterParam.getDataType());
                variableInfos.add(variableInfo);
            }
        }
        if(CollectionUtils.isNotEmpty(parameters)){
            parameterMapper.batchAddParameter(parameters);
        }
        if(CollectionUtils.isNotEmpty(variableInfos)){
            variableInfoMapper.batchAddVariable(variableInfos);
        }
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
        method.setUrl("http://127.0.0.1:8686/test/getUserById");
        method.setRequestType(RequestTypeEnum.GET);
        //入参设置
        /*List<InputParameter> inputParameters = new ArrayList<>();
        InputParameter idParm = new InputParameter();
        idParm.setKey("id");
        idParm.setName("用户ID");
        idParm.setRequired(true);
        idParm.setDataType(new DataTypeInfo(DataTypeEnum.Integer));
        idParm.setDefaultValue("666");
        inputParameters.add(idParm);
        method.setInputParameters(inputParameters);*/

        //入参填充规则
        List<FillStruct> inputFillRules = new ArrayList<>();
        FillStruct fillStruct = new FillStruct();
        fillStruct.setSource("env_id");
        fillStruct.setSourceType(FildSourceEnum.VARIABLE);
        fillStruct.setSourceDataType(new DataType(DataTypeEnum.Integer));
        fillStruct.setTarget("id");
        fillStruct.setTargetType(FildSourceEnum.FLOWINPUT);
        fillStruct.setTargetDataType(new DataType(DataTypeEnum.Integer));
        inputFillRules.add(fillStruct);
        method.setInputFillRules(inputFillRules);

        //出参设置
       /* List<OutputParameter> outputParameters = new ArrayList<>();
        OutputParameter nameParm = new OutputParameter();
        nameParm.setKey("name");
        nameParm.setName("用户名称");
        nameParm.setDataType(new DataTypeInfo(DataTypeEnum.String));
        outputParameters.add(nameParm);
        method.setOutputParameters(outputParameters);*/

        //出参填充规则
        List<FillStruct> outputFillRules = new ArrayList<>();
        FillStruct outFillStruct = new FillStruct();
        outFillStruct.setSource("name");
        outFillStruct.setSourceType(FildSourceEnum.OUTPUTPARAM);
        outFillStruct.setSourceDataType(new DataType(DataTypeEnum.String));
        outFillStruct.setTarget("env_userName");
        outFillStruct.setTargetType(FildSourceEnum.VARIABLE);
        outFillStruct.setTargetDataType(new DataType(DataTypeEnum.String));
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
        //todo 字符串的条件一定要带单引号
        conditions.put("end_5g463","env_name=='zhansan'");

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
