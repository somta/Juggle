package net.somta.juggle.console.application.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.application.assembler.IApiAssembler;
import net.somta.juggle.console.application.assembler.IFlowDefinitionAssembler;
import net.somta.juggle.console.application.service.IFlowDefinitionService;
import net.somta.juggle.console.application.service.IFlowRuntimeService;
import net.somta.juggle.console.domain.api.vo.ApiVO;
import net.somta.juggle.console.domain.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.definition.repository.IFlowDefinitionRepository;
import net.somta.juggle.console.domain.definition.vo.FlowDefinitionInfoQueryVO;
import net.somta.juggle.console.domain.definition.vo.FlowDefinitionInfoVO;
import net.somta.juggle.console.domain.flow.FlowInfoAO;
import net.somta.juggle.console.domain.flow.repository.IFlowInfoRepository;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.repository.IParameterRepository;
import net.somta.juggle.console.domain.variable.VariableInfoEntity;
import net.somta.juggle.console.domain.variable.repository.IVariableInfoRepository;
import net.somta.juggle.console.infrastructure.mapper.FlowDefinitionMapper;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
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

/**
 * @author husong
 */
@Service
public class FlowDefinitionServiceImpl implements IFlowDefinitionService {

    @Autowired
    private FlowDefinitionMapper flowDefinitionMapper;
    @Autowired
    private IFlowRuntimeService flowRuntimeService;
    @Autowired
    private IParameterRepository parameterRepository;
    @Autowired
    private IVariableInfoRepository variableInfoRepository;
    @Autowired
    private IFlowInfoRepository flowRepository;
    @Autowired
    private IFlowDefinitionRepository flowDefinitionRepository;

    @Override
    public Boolean addFlowDefinition(FlowDefinitionAddParam flowDefinitionAddParam) {
        FlowDefinitionAO flowDefinitionAo =IFlowDefinitionAssembler.IMPL.paramToAo(flowDefinitionAddParam);
        String flowKey = flowDefinitionAddParam.getFlowType() + "_" + RandomStringUtils.random(10, true, true);
        flowDefinitionAo.setFlowKey(flowKey);

        flowDefinitionAo.initParameterList(flowDefinitionAddParam.getFlowInputParams(),flowDefinitionAddParam.getFlowOutputParams());

        return flowDefinitionRepository.addFlowDefinition(flowDefinitionAo);
    }

    @Override
    public Boolean deleteFlowDefinition(Long flowDefinitionId) {
        return flowDefinitionRepository.deleteFlowDefinitionById(flowDefinitionId);
    }

    @Override
    public Boolean updateFlowDefinition(FlowDefinitionUpdateParam flowDefinitionUpdateParam) {
        FlowDefinitionAO flowDefinitionAo =IFlowDefinitionAssembler.IMPL.paramToAo(flowDefinitionUpdateParam);
        flowDefinitionAo.initParameterList(flowDefinitionUpdateParam.getFlowInputParams(),flowDefinitionUpdateParam.getFlowOutputParams());

        return flowDefinitionRepository.updateFlowDefinition(flowDefinitionAo);
    }

    @Override
    public Boolean saveFlowDefinitionContent(FlowDefinitionContentParam flowDefinitionContentParam) {
        FlowDefinitionAO flowDefinitionAo = IFlowDefinitionAssembler.IMPL.paramToAo(flowDefinitionContentParam);
        return flowDefinitionRepository.saveFlowDefinitionContent(flowDefinitionAo);
    }

    @Override
    public FlowDefinitionAO getFlowDefinitionInfo(Long flowDefinitionId) {
        FlowDefinitionAO flowDefinitionAo = flowDefinitionRepository.queryFlowDefinitionInfo(flowDefinitionId);
        return flowDefinitionAo;
    }

    @Override
    public FlowDefinitionAO getFlowDefinitionByKey(String flowKey) {
        return flowDefinitionRepository.queryFlowDefinitionByKey(flowKey);
    }

    @Override
    public PageInfo getFlowDefinitionPageList(FlowDefinitionPageParam flowDefinitionPageParam) {
        FlowDefinitionInfoQueryVO flowDefinitionInfoQueryVo = IFlowDefinitionAssembler.IMPL.paramToVo(flowDefinitionPageParam);
        Page<FlowDefinitionInfoDTO> page = PageHelper.startPage(flowDefinitionPageParam.getPageNum(), flowDefinitionPageParam.getPageSize());
        List<FlowDefinitionInfoVO> flowDefinitionList = flowDefinitionRepository.queryFlowDefinitionList(flowDefinitionInfoQueryVo);
        List<FlowDefinitionInfoDTO> flowDefinitionInfoDtoList = IFlowDefinitionAssembler.IMPL.voListToDtoList(flowDefinitionList);
        PageInfo pageInfo = new PageInfo(flowDefinitionInfoDtoList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public List<FlowDefinitionInfoPO> getFlowDefinitionList(FlowDefinitionPageParam flowDefinitionPageParam) {
        List<FlowDefinitionInfoPO> list = flowDefinitionMapper.queryByList(flowDefinitionPageParam);
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deployFlowDefinition(String flowVersion, FlowDefinitionAO flowDefinitionAo) {
        FlowInfoAO flowInfoAo = new FlowInfoAO();
        flowInfoAo.setFlowVersion(flowVersion);
        flowInfoAo.setFlowKey(flowDefinitionAo.getFlowKey());
        flowInfoAo.setFlowName(flowDefinitionAo.getFlowName());
        flowInfoAo.setFlowType(flowDefinitionAo.getFlowType());
        flowInfoAo.setFlowContent(flowDefinitionAo.getFlowContent());
        flowInfoAo.setRemark(flowDefinitionAo.getRemark());

        ParameterEntity parameterEntity = flowDefinitionAo.getParameterEntity();
        String inputParameterString = JsonSerializeHelper.serialize(parameterEntity.getFlowRuntimeInputParameters());
        flowInfoAo.setInputs(inputParameterString);
        String outputParameterString = JsonSerializeHelper.serialize(parameterEntity.getFlowRuntimeOutputParameters());
        flowInfoAo.setOutputs(outputParameterString);

        VariableInfoEntity variableInfoEntity = variableInfoRepository.queryVariableInfo(flowDefinitionAo.getId());
        String variablesString = JsonSerializeHelper.serialize(variableInfoEntity.getFlowRuntimeVariables());
        flowInfoAo.setVariables(variablesString);
        return flowRepository.deployFlow(flowInfoAo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public FlowResult debugFlow(FlowDefinitionAO flowDefinitionAo, TriggerDataParam triggerData) {
        Flow flow = new Flow();
        flow.setFlowKey(flowDefinitionAo.getFlowKey());
        flow.setFlowName(flowDefinitionAo.getFlowName());
        flow.setFlowContent(flowDefinitionAo.getFlowContent());
        flow.setInputParams(flowDefinitionAo.getParameterEntity().getFlowRuntimeInputParameters());
        flow.setOutputParams(flowDefinitionAo.getParameterEntity().getFlowRuntimeOutputParameters());

        VariableInfoEntity variableInfoEntity = variableInfoRepository.queryVariableInfo(flowDefinitionAo.getId());
        flow.setVariables(variableInfoEntity.getFlowRuntimeVariables());
        return flowRuntimeService.triggerFlow(flow, flowDefinitionAo.getFlowType(),triggerData);
    }

    @Override
    public FlowDefinition getFlowDefinitionByKey2(String flowKey) {
        //TODO 先mock一个流程定义数据，后面删除掉
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
    private static String getFlowDefinitionContent() {

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
        conditionNode.setName("判断用户名称");
        conditionNode.setElementType(ElementTypeEnum.CONDITION);
        conditionNode.setIncomings(Arrays.asList("method_8w9r3"));
        conditionNode.setOutgoings(Arrays.asList("end_5g463"));

        List<ConditionNode.ConditionItem> conditions = new ArrayList();

        ConditionNode.ConditionItem conditionItem1 = new ConditionNode.ConditionItem();
        conditionItem1.setConditionName("判断用户名称是否为zhansan");
        conditionItem1.setConditionType(ConditionNode.ConditionType.CUSTOM);
        //todo 字符串的条件一定要带单引号
        conditionItem1.setExpression("env_name==\"zhansan\"");
        conditionItem1.setOutgoing("end_5g463");
        conditions.add(conditionItem1);

        ConditionNode.ConditionItem conditionItem2 = new ConditionNode.ConditionItem();
        conditionItem2.setConditionName("判断用户名称是否为lisi");
        conditionItem2.setConditionType(ConditionNode.ConditionType.CUSTOM);
        //todo 字符串的条件一定要带单引号
        conditionItem2.setExpression("env_name==\"lisi\"");
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
        methodNode2.setName("新增用户");
        methodNode2.setElementType(ElementTypeEnum.METHOD);
        Method method2 = new Method();
        method2.setUrl("http://127.0.0.1:8686/test/addUser");
        method2.setRequestType(RequestTypeEnum.POST);

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

    public static void main(String[] args) {
        String flowContent = getFlowDefinitionContent();
        System.out.println(flowContent);

        Expression compiledExp = AviatorEvaluator.getInstance().compile("env_name==\"zhansan\"");
        Map<String, Object> env = new HashMap<>(8);
        env.put("env_name","zhansan");
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);
    }
}
