package net.somta.juggle.console.application.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.application.assembler.IFlowDefinitionAssembler;
import net.somta.juggle.console.application.service.IFlowDefinitionService;
import net.somta.juggle.console.application.service.IFlowRuntimeService;
import net.somta.juggle.console.domain.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.definition.repository.IFlowDefinitionRepository;
import net.somta.juggle.console.domain.flow.FlowAO;
import net.somta.juggle.console.domain.flow.repository.IFlowRepository;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.repository.IParameterRepository;
import net.somta.juggle.console.domain.variable.VariableInfoEntity;
import net.somta.juggle.console.domain.variable.repository.IVariableInfoRepository;
import net.somta.juggle.console.infrastructure.mapper.FlowDefinitionMapper;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.interfaces.dto.FlowDefinitionInfoDTO;
import net.somta.juggle.console.interfaces.param.*;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionAddParam;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionContentParam;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionPageParam;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionUpdateParam;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.enums.FildSourceEnum;
import net.somta.juggle.core.enums.RequestTypeEnum;
import net.somta.juggle.core.model.*;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.model.node.EndNode;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.model.node.StartNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class FlowDefinitionServiceImpl extends BaseServiceImpl<FlowDefinitionInfoPO> implements IFlowDefinitionService {

    @Autowired
    private FlowDefinitionMapper flowDefinitionMapper;
    @Autowired
    private IFlowRuntimeService flowRuntimeService;
    @Autowired
    private IParameterRepository parameterRepository;
    @Autowired
    private IVariableInfoRepository variableInfoRepository;
    @Autowired
    private IFlowRepository flowRepository;
    @Autowired
    private IFlowDefinitionRepository flowDefinitionRepository;

    @Override
    public IBaseMapper getMapper() {
        return flowDefinitionMapper;
    }

    @Override
    public Boolean addFlowDefinition(FlowDefinitionAddParam flowDefinitionAddParam) {
        FlowDefinitionAO flowDefinitionAO =IFlowDefinitionAssembler.IMPL.paramToAo(flowDefinitionAddParam);
        String flowKey = flowDefinitionAddParam.getFlowType() + "_" + RandomStringUtils.random(10, true, true);
        flowDefinitionAO.setFlowKey(flowKey);

        flowDefinitionAO.initParameterList(flowDefinitionAddParam.getFlowInputParams(),flowDefinitionAddParam.getFlowOutputParams());

        //ParameterEntity parameterEntity = new ParameterEntity();
        //parameterEntity.setInputParameterList(flowDefinitionAddParam.getFlowInputParams());
        //parameterEntity.setOutputParameterList(flowDefinitionAddParam.getFlowOutputParams());
        /*parameterEntity.setInputParameter(flowDefinitionAddParam.getFlowInputParams(),flowDefinitionAO.getId(),ParameterSourceTypeEnum.FLOW.getCode())
                .setOutputParameter(flowDefinitionAddParam.getFlowOutputParams(),flowDefinitionAO.getId(),ParameterSourceTypeEnum.FLOW.getCode());*/
        //flowDefinitionAO.setParameterEntity(parameterEntity);

        return flowDefinitionRepository.addFlowDefinition(flowDefinitionAO);
    }

    @Override
    public Boolean deleteFlowDefinition(Long flowDefinitionId) {
        return flowDefinitionRepository.deleteFlowDefinitionById(flowDefinitionId);
    }

    @Override
    public Boolean updateFlowDefinition(FlowDefinitionUpdateParam flowDefinitionUpdateParam) {
        FlowDefinitionAO flowDefinitionAO =IFlowDefinitionAssembler.IMPL.paramToAo(flowDefinitionUpdateParam);
        flowDefinitionAO.initParameterList(flowDefinitionUpdateParam.getFlowInputParams(),flowDefinitionUpdateParam.getFlowOutputParams());

        //ParameterEntity parameterEntity = new ParameterEntity();
        //parameterEntity.setInputParameterList(flowDefinitionUpdateParam.getFlowInputParams());
        //parameterEntity.setOutputParameterList(flowDefinitionUpdateParam.getFlowOutputParams());
        /*parameterEntity.setInputParameter(flowDefinitionUpdateParam.getFlowInputParams(),flowDefinitionAO.getId(),ParameterSourceTypeEnum.FLOW.getCode())
                .setOutputParameter(flowDefinitionUpdateParam.getFlowOutputParams(),flowDefinitionAO.getId(),ParameterSourceTypeEnum.FLOW.getCode());*/
        //flowDefinitionAO.setParameterEntity(parameterEntity);

        return flowDefinitionRepository.updateFlowDefinition(flowDefinitionAO);
    }

    @Override
    public Boolean saveFlowDefinitionContent(FlowDefinitionContentParam flowDefinitionContentParam) {
        FlowDefinitionAO flowDefinitionAO = IFlowDefinitionAssembler.IMPL.paramToAo(flowDefinitionContentParam);
        return flowDefinitionRepository.saveFlowDefinitionContent(flowDefinitionAO);
    }

    @Override
    public FlowDefinitionAO getFlowDefinitionInfo(Long flowDefinitionId) {
        FlowDefinitionAO flowDefinitionAO = flowDefinitionRepository.queryFlowDefinitionInfo(flowDefinitionId);
        //todo 这里的转换不对，缺少参数，应该把entity中的po改成vo
        FlowDefinitionInfoDTO flowDefinitionInfoDTO = IFlowDefinitionAssembler.IMPL.aoToDto(flowDefinitionAO);
        return flowDefinitionAO;
    }

    @Override
    public FlowDefinitionInfoPO getFlowDefinitionByKey(String flowKey) {
        return flowDefinitionMapper.queryFlowDefinitionByKey(flowKey);
    }

    @Override
    public List<FlowDefinitionInfoPO> getFlowDefinitionList(FlowDefinitionPageParam flowDefinitionPageParam) {
        List<FlowDefinitionInfoPO> list = flowDefinitionMapper.queryByList(flowDefinitionPageParam);
        return list;
    }

    @Transactional
    @Override
    public Boolean deployFlowDefinition(String flowVersion, FlowDefinitionAO flowDefinitionAO) {
        FlowAO flowAO = new FlowAO();
        flowAO.setFlowVersion(flowVersion);

        ParameterEntity parameterEntity = flowDefinitionAO.getParameterEntity();
        String inputParameterString = JsonSerializeHelper.serialize(parameterEntity.getFlowRuntimeInputParameters());
        flowAO.setInputs(inputParameterString);
        String outputParameterString = JsonSerializeHelper.serialize(parameterEntity.getFlowRuntimeOutputParameters());
        flowAO.setOutputs(outputParameterString);

        VariableInfoEntity variableInfoEntity = variableInfoRepository.queryVariableInfo(flowDefinitionAO.getId());
        String variablesString = JsonSerializeHelper.serialize(variableInfoEntity.getFlowRuntimeVariables());
        flowAO.setVariables(variablesString);

        //TODO 这里要转成流程的AO
       /* FlowInfoPO flowInfoPO = new FlowInfoPO();
        flowInfoPO.setFlowKey(flowDefinitionInfoPO.getFlowKey());
        flowInfoPO.setFlowName(flowDefinitionInfoPO.getFlowName());
        flowInfoPO.setRemark(flowDefinitionInfoPO.getRemark());

        ParameterEntity parameterEntity = flowDefinitionAO.getParameterEntity();
        String inputParameterString = JsonSerializeHelper.serialize(parameterEntity.getFlowRuntimeInputParameters());
        flowInfoPO.setInputs(inputParameterString);
        String outputParameterString = JsonSerializeHelper.serialize(parameterEntity.getFlowRuntimeOutputParameters());
        flowInfoPO.setOutputs(outputParameterString);

        VariableInfoEntity variableInfoEntity = variableInfoRepository.queryVariableInfo(flowDefinitionInfoPO.getId());
        String variablesString = JsonSerializeHelper.serialize(variableInfoEntity.getFlowRuntimeVariables());
        flowInfoPO.setVariables(variablesString);*/

        return flowRepository.deployFlow(flowAO);
    }

    @Transactional
    @Override
    public FlowResult debugFlow(FlowDefinitionInfoPO flowDefinitionInfoPO, TriggerDataParam triggerData) {
        Flow flow = new Flow();
        flow.setFlowKey(flowDefinitionInfoPO.getFlowKey());
        flow.setFlowName(flowDefinitionInfoPO.getFlowName());
        flow.setFlowContent(flowDefinitionInfoPO.getFlowContent());

        ParameterEntity parameterEntity = parameterRepository.getParameter(new ParameterVO(ParameterSourceTypeEnum.FLOW.getCode(), flowDefinitionInfoPO.getId()));
        flow.setInputParams(parameterEntity.getFlowRuntimeInputParameters());
        flow.setOutputParams(parameterEntity.getFlowRuntimeOutputParameters());

        VariableInfoEntity variableInfoEntity = variableInfoRepository.queryVariableInfo(flowDefinitionInfoPO.getId());
        flow.setVariables(variableInfoEntity.getFlowRuntimeVariables());
        return flowRuntimeService.triggerFlow(flow, flowDefinitionInfoPO.getFlowType(),triggerData);
    }

    @Override
    public FlowDefinition getFlowDefinitionByKey2(String flowKey) {
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
