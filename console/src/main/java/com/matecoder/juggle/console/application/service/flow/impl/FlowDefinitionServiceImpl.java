/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package com.matecoder.juggle.console.application.service.flow.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matecoder.common.utils.JsonUtil;
import com.matecoder.juggle.console.application.assembler.flow.IFlowDefinitionAssembler;
import com.matecoder.juggle.console.application.service.flow.IFlowDefinitionService;
import com.matecoder.juggle.console.application.service.flow.IFlowRuntimeService;
import com.matecoder.juggle.console.domain.flow.definition.FlowDefinitionAO;
import com.matecoder.juggle.console.domain.flow.definition.repository.IFlowDefinitionRepository;
import com.matecoder.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoQueryVO;
import com.matecoder.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoVO;
import com.matecoder.juggle.console.domain.flow.flowinfo.FlowInfoAO;
import com.matecoder.juggle.console.domain.flow.flowinfo.repository.IFlowInfoRepository;
import com.matecoder.juggle.console.domain.parameter.ParameterEntity;
import com.matecoder.juggle.console.domain.flow.definition.repository.IVariableInfoRepository;
import com.matecoder.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import com.matecoder.juggle.console.interfaces.dto.flow.FlowDefinitionInfoDTO;
import com.matecoder.juggle.common.param.TriggerDataParam;
import com.matecoder.juggle.console.interfaces.param.flow.definition.*;
import com.matecoder.juggle.core.model.Flow;
import com.matecoder.juggle.core.model.FlowResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author husong
 * @since 1.0.0
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
        flowDefinitionAo.setFlowKey(flowDefinitionAo.generateFlowKey());
        flowDefinitionAo.initDefaultFlowContent(flowDefinitionAddParam.getFlowName());
        flowDefinitionAo.initParameterList(flowDefinitionAddParam.getFlowInputParams(),flowDefinitionAddParam.getFlowOutputParams());

        flowDefinitionRepository.addFlowDefinition(flowDefinitionAo);
        return true;
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
        try {
            flowDefinitionAo.processFlowContent();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public Long createFlowDefinitionByTemplate(FlowDefinitionAO flowDefinitionAo) {
        return flowDefinitionRepository.addFlowDefinition(flowDefinitionAo);
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
        try {
            String inputParameterString = JsonUtil.serialize(parameterEntity.getInputParameterSchema());
            flowInfoAo.setInputs(inputParameterString);
            String outputParameterString = JsonUtil.serialize(parameterEntity.getOutputParameterSchema());
            flowInfoAo.setOutputs(outputParameterString);

            String variablesString = JsonUtil.serialize(flowDefinitionAo.getFlowRuntimeVariables());
            flowInfoAo.setVariables(variablesString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return flowRepository.deployFlow(flowInfoAo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public FlowResult debugFlow(FlowDefinitionAO flowDefinitionAo, TriggerDataParam triggerData) {
        Flow flow = new Flow();
        flow.setFlowKey(flowDefinitionAo.getFlowKey());
        flow.setFlowName(flowDefinitionAo.getFlowName());
        flow.setFlowContent(flowDefinitionAo.getFlowContent());
        flow.setInputParams(flowDefinitionAo.getParameterEntity().getInputParameterSchema());
        flow.setOutputParams(flowDefinitionAo.getParameterEntity().getOutputParameterSchema());
        flow.setVariables(flowDefinitionAo.getFlowRuntimeVariables());
        return flowRuntimeService.triggerFlow(flow, flowDefinitionAo.getFlowType(),triggerData);
    }

}
