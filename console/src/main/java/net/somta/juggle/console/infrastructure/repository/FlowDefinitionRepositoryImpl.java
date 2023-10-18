package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.definition.repository.IFlowDefinitionRepository;
import net.somta.juggle.console.domain.definition.vo.FlowDefinitionInfoQueryVO;
import net.somta.juggle.console.domain.definition.vo.FlowDefinitionInfoVO;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.domain.parameter.repository.IParameterRepository;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.domain.variable.enums.VariableTypeEnum;
import net.somta.juggle.console.infrastructure.converter.IFlowDefinitionConverter;
import net.somta.juggle.console.infrastructure.mapper.FlowDefinitionMapper;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.mapper.VariableInfoMapper;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author husong
 */
@Component
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
    public Boolean addFlowDefinition(FlowDefinitionAO flowDefinitionAO) {
        FlowDefinitionInfoPO flowDefinitionInfoPO = IFlowDefinitionConverter.IMPL.aoToPo(flowDefinitionAO);
        flowDefinitionInfoPO.setCreatedAt(new Date());
        flowDefinitionMapper.addFlowDefinitionInfo(flowDefinitionInfoPO);

        saveParametersAndVariables(flowDefinitionInfoPO.getId(),flowDefinitionAO);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteFlowDefinitionById(Long flowDefinitionId) {
        flowDefinitionMapper.deleteById(flowDefinitionId);
        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.FLOW.getCode(),flowDefinitionId));
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateFlowDefinition(FlowDefinitionAO flowDefinitionAO) {
        FlowDefinitionInfoPO flowDefinitionInfoPO = IFlowDefinitionConverter.IMPL.aoToPo(flowDefinitionAO);
        flowDefinitionMapper.update(flowDefinitionInfoPO);
        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.FLOW.getCode(), flowDefinitionAO.getId()));
        variableInfoMapper.deleteVariableByFlowDefinitionId(flowDefinitionAO.getId());
        saveParametersAndVariables(flowDefinitionInfoPO.getId(),flowDefinitionAO);
        return true;
    }

    @Override
    public Boolean saveFlowDefinitionContent(FlowDefinitionAO flowDefinitionAO) {
        FlowDefinitionInfoPO flowDefinitionInfoPO = IFlowDefinitionConverter.IMPL.aoToPo(flowDefinitionAO);
        flowDefinitionMapper.update(flowDefinitionInfoPO);
        return true;
    }

    @Override
    public FlowDefinitionAO queryFlowDefinitionInfo(Long flowDefinitionId) {
        FlowDefinitionInfoPO flowDefinitionInfoPO = flowDefinitionMapper.queryById(flowDefinitionId);
        FlowDefinitionAO flowDefinitionAO = IFlowDefinitionConverter.IMPL.poToAo(flowDefinitionInfoPO);
        ParameterEntity parameterEntity = parameterRepository.getParameter(new ParameterVO(ParameterSourceTypeEnum.FLOW.getCode(), flowDefinitionId));
        flowDefinitionAO.setParameterEntity(parameterEntity);
        return flowDefinitionAO;
    }

    @Override
    public List<FlowDefinitionInfoVO> queryFlowDefinitionList(FlowDefinitionInfoQueryVO flowDefinitionInfoQueryVO) {
        return flowDefinitionMapper.queryFlowDefinitionList(flowDefinitionInfoQueryVO);
    }

    private void saveParametersAndVariables(Long flowDefinitionId,FlowDefinitionAO flowDefinitionAO){
        List<ParameterPO> parameterPoList = flowDefinitionAO.getParameterEntity().getParameterPoList(flowDefinitionId,ParameterSourceTypeEnum.FLOW.getCode());
        List<VariableInfoPO> variableInfoPOList = new ArrayList<>();
        flowDefinitionAO.getParameterEntity().getInputParameterList();
        if(CollectionUtils.isNotEmpty(parameterPoList)){
            parameterPoList.stream().forEach(parameter -> {
                parameter.setSourceId(flowDefinitionId);
                VariableInfoPO variableInfoPO = new VariableInfoPO();
                variableInfoPO.setFlowDefinitionId(flowDefinitionId);
                variableInfoPO.setEnvKey(parameter.getParamKey());
                variableInfoPO.setEnvName(parameter.getParamName());
                variableInfoPO.setEnvType(parameter.getParamType() == ParameterTypeEnum.INPUT_PARAM.getCode() ? VariableTypeEnum.INPUT_PARAM_VARIABLE.getCode() : VariableTypeEnum.OUTPUT_PARAM_VARIABLE.getCode());
                variableInfoPO.setDataType(parameter.getDataType());
                variableInfoPOList.add(variableInfoPO);
            });
            parameterMapper.batchAddParameter(parameterPoList);

            if(CollectionUtils.isNotEmpty(variableInfoPOList)){
                variableInfoMapper.batchAddVariable(variableInfoPOList);
            }
        }
    }
}
