package net.somta.juggle.console.application.service.flow.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.application.assembler.flow.IFlowDefinitionAssembler;
import net.somta.juggle.console.application.service.flow.IFlowDefinitionService;
import net.somta.juggle.console.application.service.flow.IFlowRuntimeService;
import net.somta.juggle.console.domain.flow.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.flow.definition.repository.IFlowDefinitionRepository;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoQueryVO;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoVO;
import net.somta.juggle.console.domain.flow.flowinfo.FlowInfoAO;
import net.somta.juggle.console.domain.flow.flowinfo.repository.IFlowInfoRepository;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.variable.repository.IVariableInfoRepository;
import net.somta.juggle.console.domain.variable.vo.VariableInfoVO;
import net.somta.juggle.console.interfaces.dto.flow.FlowDefinitionInfoDTO;
import net.somta.juggle.console.interfaces.param.flow.TriggerDataParam;
import net.somta.juggle.console.interfaces.param.flow.definition.*;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author husong
 */
@Service
public class FlowDefinitionServiceImpl implements IFlowDefinitionService {

    private final IFlowRuntimeService flowRuntimeService;
    private final IVariableInfoRepository variableInfoRepository;
    private final IFlowInfoRepository flowRepository;
    private final IFlowDefinitionRepository flowDefinitionRepository;

    public FlowDefinitionServiceImpl(IFlowRuntimeService flowRuntimeService, IVariableInfoRepository variableInfoRepository, IFlowInfoRepository flowRepository, IFlowDefinitionRepository flowDefinitionRepository) {
        this.flowRuntimeService = flowRuntimeService;
        this.variableInfoRepository = variableInfoRepository;
        this.flowRepository = flowRepository;
        this.flowDefinitionRepository = flowDefinitionRepository;
    }

    @Override
    public Boolean addFlowDefinition(FlowDefinitionAddParam flowDefinitionAddParam) {
        FlowDefinitionAO flowDefinitionAo =IFlowDefinitionAssembler.IMPL.paramToAo(flowDefinitionAddParam);
        String flowKey = flowDefinitionAddParam.getFlowType() + "_" + RandomStringUtils.random(10, true, true);
        flowDefinitionAo.setFlowKey(flowKey);
        flowDefinitionAo.initDefaultFlowContent(flowDefinitionAddParam.getFlowName());
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
        List<VariableInfoVO> variableInfoVoList = variableInfoRepository.queryVariableInfoList(flowDefinitionAo.getId());
        flowDefinitionAo.setVariableInfoList(variableInfoVoList);
        return flowDefinitionAo;
    }

    @Override
    public FlowDefinitionAO getFlowDefinitionByKey(String flowKey) {
        FlowDefinitionAO flowDefinitionAo = flowDefinitionRepository.queryFlowDefinitionByKey(flowKey);
        List<VariableInfoVO> variableInfoVoList = variableInfoRepository.queryVariableInfoList(flowDefinitionAo.getId());
        flowDefinitionAo.setVariableInfoList(variableInfoVoList);
        return flowDefinitionAo;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deployFlowDefinition(FlowDefinitionDeployParam flowDefinitionDeployParam, FlowDefinitionAO flowDefinitionAo) {
        FlowInfoAO flowInfoAo = new FlowInfoAO();
        flowInfoAo.setFlowVersion(flowDefinitionDeployParam.getFlowDeployVersion());
        flowInfoAo.setFlowKey(flowDefinitionAo.getFlowKey());
        flowInfoAo.setFlowName(flowDefinitionAo.getFlowName());
        flowInfoAo.setFlowType(flowDefinitionAo.getFlowType());
        flowInfoAo.setFlowContent(flowDefinitionAo.getFlowContent());
        flowInfoAo.setRemark(flowDefinitionAo.getRemark());
        flowInfoAo.setFlowVersionRemark(flowDefinitionDeployParam.getFlowVersionRemark());

        ParameterEntity parameterEntity = flowDefinitionAo.getParameterEntity();
        String inputParameterString = JsonSerializeHelper.serialize(parameterEntity.getFlowRuntimeInputParameters());
        flowInfoAo.setInputs(inputParameterString);
        String outputParameterString = JsonSerializeHelper.serialize(parameterEntity.getFlowRuntimeOutputParameters());
        flowInfoAo.setOutputs(outputParameterString);

        String variablesString = JsonSerializeHelper.serialize(flowDefinitionAo.getFlowRuntimeVariables());
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
        flow.setVariables(flowDefinitionAo.getFlowRuntimeVariables());
        return flowRuntimeService.triggerFlow(flow, flowDefinitionAo.getFlowType(),triggerData);
    }

}
