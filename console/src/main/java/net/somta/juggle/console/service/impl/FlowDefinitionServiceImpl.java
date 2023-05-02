package net.somta.juggle.console.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.common.utils.SnowflakeIdUtil;
import net.somta.juggle.console.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.enums.ParameterTypeEnum;
import net.somta.juggle.console.mapper.FlowDefinitionMapper;
import net.somta.juggle.console.mapper.FlowMapper;
import net.somta.juggle.console.mapper.ParameterMapper;
import net.somta.juggle.console.mapper.VariableInfoMapper;
import net.somta.juggle.console.model.FlowDefinitionInfo;
import net.somta.juggle.console.model.FlowInfo;
import net.somta.juggle.console.model.Parameter;
import net.somta.juggle.console.model.VariableInfo;
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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlowDefinitionServiceImpl implements IFlowDefinitionService {

    @Autowired
    private FlowDefinitionMapper flowDefinitionMapper;
    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private ParameterMapper parameterMapper;
    @Autowired
    private SnowflakeIdUtil snowflakeIdUtil;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VariableInfoMapper variableInfoMapper;

    @Transactional
    @Override
    public Boolean addFlowDefinition(FlowDefinitionParam flowDefinitionParam) {
        //todo 后面在把FlowDefinitionInfo名称改成FlowDefinition
        FlowDefinitionInfo flowDefinition = new FlowDefinitionInfo();
        Long flowDefinitionId = snowflakeIdUtil.nextId();
        flowDefinition.setId(flowDefinitionId);
        String flowKey = RandomStringUtils.random(10, true, true);
        flowDefinition.setFlowKey(flowKey);
        flowDefinition.setFlowName(flowDefinitionParam.getFlowName());
        flowDefinitionMapper.addFlowDefinition(flowDefinition);
        saveParameters(flowDefinitionId,flowDefinitionParam.getInputs(), flowDefinitionParam.getOutputs());
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteFlowDefinition(Long flowDefinitionId) {
        flowDefinitionMapper.deleteFlowDefinitionById(flowDefinitionId);
        ParameterVO parameterVO = new ParameterVO();
        parameterVO.setSourceType(ParameterSourceTypeEnum.FLOW.getCode());
        parameterVO.setSourceId(flowDefinitionId);
        parameterMapper.deleteParameter(parameterVO);
        return true;
    }

    @Override
    public Boolean updateFlowDefinition(FlowDefinitionParam flowDefinitionParam) {
        flowDefinitionMapper.updateFlowDefinitionById(flowDefinitionParam);
        ParameterVO parameterVO = new ParameterVO();
        parameterVO.setSourceType(ParameterSourceTypeEnum.FLOW.getCode());
        parameterVO.setSourceId(flowDefinitionParam.getId());
        parameterMapper.deleteParameter(parameterVO);
        saveParameters(flowDefinitionParam.getId(),flowDefinitionParam.getInputs(), flowDefinitionParam.getOutputs());
        return true;
    }

    @Override
    public FlowDefinitionInfo getFlowDefinitionById(Long flowDefinitionId) {
        return flowDefinitionMapper.queryFlowDefinitionById(flowDefinitionId);
    }

    @Override
    public List<FlowDefinitionInfo> getFlowDefinitionList(FlowDefinitionPageParam flowDefinitionPageParam) {
        List<FlowDefinitionInfo> list = flowDefinitionMapper.queryFlowDefinitionList(flowDefinitionPageParam);
        return list;
    }


    // todo 在这里转成流程执行运行需要的对象，而不是在触发流程哪里转
    @Transactional
    @Override
    public Boolean deployFlowDefinition(FlowDefinitionInfo flowDefinitionInfo,String version) {
        FlowInfo flowInfo = new FlowInfo();
        flowInfo.setId(snowflakeIdUtil.nextId());
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
            List<Parameter> inputParameterList = parameterList.stream()
                    .filter(parameter -> ParameterTypeEnum.INPUT_PARAM.getCode() == parameter.getParamType()).collect(Collectors.toList());
            List<Parameter> outputParameterList = parameterList.stream()
                    .filter(parameter -> ParameterTypeEnum.OUTPUT_PARAM.getCode() == parameter.getParamType()).collect(Collectors.toList());
            try {
                String inputParameterString = objectMapper.writeValueAsString(inputParameterList);
                flowInfo.setInputs(inputParameterString);
                String outputParameterString = objectMapper.writeValueAsString(outputParameterList);
                flowInfo.setOutputs(outputParameterString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        //处理变量
        List<VariableInfo> variableInfoList = variableInfoMapper.queryVariableInfoListByDefinitionId(flowDefinitionInfo.getId());
        if(CollectionUtils.isNotEmpty(variableInfoList)){
            String variableInfoListString = null;
            try {
                variableInfoListString = objectMapper.writeValueAsString(variableInfoList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            flowInfo.setVariables(variableInfoListString);
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
    private void saveParameters(Long flowDefinitionId,List<InputParameterParam> inputParameterList,
                                List<OutputParameterParam> outputParameterList){
        List<Parameter> parameters = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(inputParameterList)){
            for (InputParameterParam inputParameterParam : inputParameterList) {
                Parameter parameter = new Parameter();
                parameter.setId(snowflakeIdUtil.nextId());
                parameter.setParamType(ParameterTypeEnum.INPUT_PARAM.getCode());
                parameter.setParamName(inputParameterParam.getParamName());
                parameter.setDataType(inputParameterParam.getDataType());
                parameter.setRequired(inputParameterParam.getRequired());
                parameter.setSourceType(ParameterSourceTypeEnum.FLOW.getCode());
                parameter.setSourceId(flowDefinitionId);
                parameters.add(parameter);
            }
        }
        if(CollectionUtils.isNotEmpty(outputParameterList)){
            for (OutputParameterParam outputParameterParam : outputParameterList) {
                Parameter parameter = new Parameter();
                parameter.setId(snowflakeIdUtil.nextId());
                parameter.setParamType(ParameterTypeEnum.OUTPUT_PARAM.getCode());
                parameter.setParamName(outputParameterParam.getParamName());
                parameter.setDataType(outputParameterParam.getDataType());
                parameter.setSourceType(ParameterSourceTypeEnum.FLOW.getCode());
                parameter.setSourceId(flowDefinitionId);
                parameters.add(parameter);
            }
        }
        if(CollectionUtils.isNotEmpty(parameters)){
            parameterMapper.batchAddParameter(parameters);
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
