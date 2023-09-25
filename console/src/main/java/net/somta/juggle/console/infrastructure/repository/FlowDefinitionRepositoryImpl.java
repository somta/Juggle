package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.definition.repository.IFlowDefinitionRepository;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.infrastructure.mapper.FlowDefinitionMapper;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.po.ApiPO;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
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

    public FlowDefinitionRepositoryImpl(FlowDefinitionMapper flowDefinitionMapper, ParameterMapper parameterMapper) {
        this.flowDefinitionMapper = flowDefinitionMapper;
        this.parameterMapper = parameterMapper;
    }


    @Transactional
    @Override
    public Boolean addFlowDefinition(FlowDefinitionAO flowDefinitionAO) {
        FlowDefinitionInfoPO flowDefinitionInfoPO = new FlowDefinitionInfoPO();
        flowDefinitionInfoPO.setFlowKey(flowDefinitionAO.getFlowKey());
        flowDefinitionInfoPO.setFlowName(flowDefinitionAO.getFlowName());
        flowDefinitionInfoPO.setFlowType(flowDefinitionAO.getFlowType());
        flowDefinitionInfoPO.setRemark(flowDefinitionAO.getRemark());
        flowDefinitionInfoPO.setCreatedAt(new Date());
        flowDefinitionMapper.addFlowDefinitionInfo(flowDefinitionInfoPO);

        List<ParameterPO> parameterPOS = new ArrayList<>();
        parameterPOS.addAll(flowDefinitionAO.getParameterEntity().getInputParameterList());
        parameterPOS.addAll(flowDefinitionAO.getParameterEntity().getOutputParameterList());
        if(CollectionUtils.isNotEmpty(parameterPOS)){
            parameterPOS.stream().forEach(parameter -> parameter.setSourceId(flowDefinitionInfoPO.getId()));
            parameterMapper.batchAddParameter(parameterPOS);
        }
        //todo  这里还要把入参和出参转成变量
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteFlowDefinitionById(Long flowDefinitionId) {
        flowDefinitionMapper.deleteById(flowDefinitionId);
        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.FLOW.getCode(),flowDefinitionId));
        return true;
    }
}
