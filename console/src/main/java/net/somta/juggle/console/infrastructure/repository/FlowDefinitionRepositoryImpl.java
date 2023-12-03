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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author husong
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
    public Boolean addFlowDefinition(FlowDefinitionAO flowDefinitionAo) {
        FlowDefinitionInfoPO flowDefinitionInfoPo = IFlowDefinitionConverter.IMPL.aoToPo(flowDefinitionAo);
        flowDefinitionInfoPo.setCreatedAt(new Date());
        flowDefinitionMapper.addFlowDefinitionInfo(flowDefinitionInfoPo);

        saveParametersAndVariables(flowDefinitionInfoPo.getId(),flowDefinitionAo);
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
    public Boolean updateFlowDefinition(FlowDefinitionAO flowDefinitionAo) {
        FlowDefinitionInfoPO flowDefinitionInfoPo = IFlowDefinitionConverter.IMPL.aoToPo(flowDefinitionAo);
        flowDefinitionMapper.update(flowDefinitionInfoPo);
        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.FLOW.getCode(), flowDefinitionAo.getId()));
        variableInfoMapper.deleteVariableByFlowDefinitionId(flowDefinitionAo.getId());
        saveParametersAndVariables(flowDefinitionInfoPo.getId(),flowDefinitionAo);
        return true;
    }

    @Override
    public Boolean saveFlowDefinitionContent(FlowDefinitionAO flowDefinitionAo) {
        FlowDefinitionInfoPO flowDefinitionInfoPo = IFlowDefinitionConverter.IMPL.aoToPo(flowDefinitionAo);
        flowDefinitionMapper.update(flowDefinitionInfoPo);
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
        flowDefinitionAo.getParameterEntity().getInputParameterList();
        if(CollectionUtils.isNotEmpty(parameterPoList)){
            parameterPoList.stream().forEach(parameter -> {
                parameter.setSourceId(flowDefinitionId);
                VariableInfoPO variableInfoPo = new VariableInfoPO();
                variableInfoPo.setFlowDefinitionId(flowDefinitionId);
                variableInfoPo.setEnvKey(parameter.getParamKey());
                variableInfoPo.setEnvName(parameter.getParamName());
                variableInfoPo.setEnvType(parameter.getParamType() == ParameterTypeEnum.INPUT_PARAM.getCode() ? VariableTypeEnum.INPUT_PARAM_VARIABLE.getCode() : VariableTypeEnum.OUTPUT_PARAM_VARIABLE.getCode());
                variableInfoPo.setDataType(parameter.getDataType());
                variableInfoPoList.add(variableInfoPo);
            });
            parameterMapper.batchAddParameter(parameterPoList);

            if(CollectionUtils.isNotEmpty(variableInfoPoList)){
                variableInfoMapper.batchAddVariable(variableInfoPoList);
            }
        }
    }
}
