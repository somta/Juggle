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
package net.somta.juggle.console.infrastructure.repository.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.flow.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.flow.definition.repository.IFlowDefinitionRepository;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoQueryVO;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoVO;
import net.somta.juggle.console.domain.flow.definition.vo.VariableDeleteVO;
import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.domain.parameter.repository.IParameterRepository;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.domain.flow.definition.enums.VariableTypeEnum;
import net.somta.juggle.console.infrastructure.converter.IVariableInfoConverter;
import net.somta.juggle.console.infrastructure.converter.flow.IFlowDefinitionConverter;
import net.somta.juggle.console.infrastructure.mapper.flow.FlowDefinitionMapper;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.mapper.VariableInfoMapper;
import net.somta.juggle.console.infrastructure.po.flow.FlowDefinitionInfoPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import net.somta.juggle.core.enums.VariablePrefixEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author husong
 * @since 1.0.0
 */
@Repository
public class FlowDefinitionRepositoryImpl implements IFlowDefinitionRepository {

    private final FlowDefinitionMapper flowDefinitionMapper;
    private final ParameterMapper parameterMapper;
    private final VariableInfoMapper variableInfoMapper;
    private final IParameterRepository parameterRepository;

    public FlowDefinitionRepositoryImpl(FlowDefinitionMapper flowDefinitionMapper, ParameterMapper parameterMapper, VariableInfoMapper variableInfoMapper, IParameterRepository parameterRepository) {
        this.flowDefinitionMapper = flowDefinitionMapper;
        this.parameterMapper = parameterMapper;
        this.variableInfoMapper = variableInfoMapper;
        this.parameterRepository = parameterRepository;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long addFlowDefinition(FlowDefinitionAO flowDefinitionAo) {
        FlowDefinitionInfoPO flowDefinitionInfoPo = IFlowDefinitionConverter.IMPL.aoToPo(flowDefinitionAo);
        flowDefinitionInfoPo.setCreatedAt(new Date());
        flowDefinitionInfoPo.setCreatedBy(IdentityContext.getIdentity().getUserId());
        Long flowDefinitionId = flowDefinitionMapper.addFlowDefinitionInfo(flowDefinitionInfoPo);

        saveParametersAndVariables(flowDefinitionInfoPo.getId(),flowDefinitionAo);
        return flowDefinitionId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteFlowDefinitionById(Long flowDefinitionId) {
        FlowDefinitionInfoPO flowDefinitionInfoPo = new FlowDefinitionInfoPO();
        flowDefinitionInfoPo.setId(flowDefinitionId);
        flowDefinitionInfoPo.setDeleted(1);
        flowDefinitionMapper.update(flowDefinitionInfoPo);
        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.FLOW.getCode(),flowDefinitionId));
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateFlowDefinition(FlowDefinitionAO flowDefinitionAo) {
        FlowDefinitionInfoPO flowDefinitionInfoPo = IFlowDefinitionConverter.IMPL.aoToPo(flowDefinitionAo);
        flowDefinitionInfoPo.setUpdatedAt(new Date());
        flowDefinitionInfoPo.setUpdatedBy(IdentityContext.getIdentity().getUserId());
        flowDefinitionMapper.update(flowDefinitionInfoPo);
        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.FLOW.getCode(), flowDefinitionAo.getId()));
        variableInfoMapper.deleteVariableByFlowDefinitionId(new VariableDeleteVO(flowDefinitionAo.getId(),3));
        saveParametersAndVariables(flowDefinitionInfoPo.getId(),flowDefinitionAo);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveFlowDefinitionContent(FlowDefinitionAO flowDefinitionAo) {
        FlowDefinitionInfoPO flowDefinitionInfoPo = IFlowDefinitionConverter.IMPL.aoToPo(flowDefinitionAo);
        flowDefinitionMapper.update(flowDefinitionInfoPo);
        variableInfoMapper.deleteVariableByFlowDefinitionId(new VariableDeleteVO(flowDefinitionAo.getId(),null));
        List<VariableInfoPO> variableInfoPoList = IVariableInfoConverter.IMPL.voListToPoList(flowDefinitionAo.getVariableInfoList(),flowDefinitionAo.getId());
        if(CollectionUtils.isNotEmpty(variableInfoPoList)){
            variableInfoMapper.batchAddVariable(variableInfoPoList);
        }
        return true;
    }

    @Override
    public FlowDefinitionAO queryFlowDefinitionInfo(Long flowDefinitionId) {
        FlowDefinitionInfoPO flowDefinitionInfoPo = flowDefinitionMapper.queryById(flowDefinitionId);
        FlowDefinitionAO flowDefinitionAo = IFlowDefinitionConverter.IMPL.poToAo(flowDefinitionInfoPo);
        ParameterEntity parameterEntity = parameterRepository.getParameter(new ParameterVO(ParameterSourceTypeEnum.FLOW.getCode(), flowDefinitionId));
        flowDefinitionAo.setParameterEntity(parameterEntity);
        return flowDefinitionAo;
    }

    @Override
    public FlowDefinitionAO queryFlowDefinitionByKey(String flowKey) {
        FlowDefinitionInfoPO flowDefinitionInfoPo = flowDefinitionMapper.queryFlowDefinitionByKey(flowKey);
        FlowDefinitionAO flowDefinitionAo = IFlowDefinitionConverter.IMPL.poToAo(flowDefinitionInfoPo);
        ParameterEntity parameterEntity = parameterRepository.getParameter(new ParameterVO(ParameterSourceTypeEnum.FLOW.getCode(), flowDefinitionInfoPo.getId()));
        flowDefinitionAo.setParameterEntity(parameterEntity);
        return flowDefinitionAo;
    }

    @Override
    public List<FlowDefinitionInfoVO> queryFlowDefinitionList(FlowDefinitionInfoQueryVO flowDefinitionInfoQueryVO) {
        return flowDefinitionMapper.queryFlowDefinitionList(flowDefinitionInfoQueryVO);
    }

    private void saveParametersAndVariables(Long flowDefinitionId,FlowDefinitionAO flowDefinitionAo){
        List<ParameterPO> parameterPoList = flowDefinitionAo.getParameterEntity().getParameterPoList(flowDefinitionId,ParameterSourceTypeEnum.FLOW.getCode());
        List<VariableInfoPO> variableInfoPoList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(flowDefinitionAo.getVariableInfoList())){
            List<VariableInfoVO> middleVariableList = flowDefinitionAo.getVariableInfoList().stream()
                    .filter(variable -> VariableTypeEnum.MIDDLE_VARIABLE.getCode() == variable.getEnvType())
                    .collect(Collectors.toList());

            VariableInfoPO middleVariableInfoPo;
            for (VariableInfoVO variableInfo :middleVariableList){
                middleVariableInfoPo = new VariableInfoPO();
                middleVariableInfoPo.setFlowDefinitionId(flowDefinitionId);
                middleVariableInfoPo.setEnvKey(variableInfo.getEnvKey());
                middleVariableInfoPo.setEnvName(variableInfo.getEnvName());
                middleVariableInfoPo.setEnvType(VariableTypeEnum.MIDDLE_VARIABLE.getCode());
                try {
                    middleVariableInfoPo.setDataType(JsonSerializeHelper.serialize(variableInfo.getDataType()));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                variableInfoPoList.add(middleVariableInfoPo);
            }
        }

        if(CollectionUtils.isNotEmpty(parameterPoList)){
            parameterPoList.stream().forEach(parameter -> {
                parameter.setSourceId(flowDefinitionId);
                VariableInfoPO variableInfoPo = new VariableInfoPO();
                variableInfoPo.setFlowDefinitionId(flowDefinitionId);
                if(parameter.getParamType() == ParameterTypeEnum.INPUT_PARAM.getCode()){
                    variableInfoPo.setEnvKey(VariablePrefixEnum.INPUT_VARIABLE_PREFIX.getCode() + parameter.getParamKey());
                }else if(parameter.getParamType() == ParameterTypeEnum.OUTPUT_PARAM.getCode()){
                    variableInfoPo.setEnvKey(VariablePrefixEnum.OUTPUT_VARIABLE_PREFIX.getCode() + parameter.getParamKey());
                }else {
                    variableInfoPo.setEnvKey(parameter.getParamKey());
                }
                variableInfoPo.setEnvName(parameter.getParamName());
                variableInfoPo.setEnvType(parameter.getParamType() == ParameterTypeEnum.INPUT_PARAM.getCode() ? VariableTypeEnum.INPUT_PARAM_VARIABLE.getCode() : VariableTypeEnum.OUTPUT_PARAM_VARIABLE.getCode());
                variableInfoPo.setDataType(parameter.getDataType());
                variableInfoPoList.add(variableInfoPo);
            });
            parameterMapper.batchAddParameter(parameterPoList);
        }

        if(CollectionUtils.isNotEmpty(variableInfoPoList)){
            variableInfoMapper.batchAddVariable(variableInfoPoList);
        }
    }
}
