package net.somta.juggle.console.domain.flow.definition.repository;

import net.somta.juggle.console.domain.flow.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoQueryVO;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoVO;

import java.util.List;

/**
 * @author husong
 */
public interface IFlowDefinitionRepository {
    Boolean addFlowDefinition(FlowDefinitionAO flowDefinitionAo);

    Boolean deleteFlowDefinitionById(Long flowDefinitionId);

    Boolean updateFlowDefinition(FlowDefinitionAO flowDefinitionAo);

    Boolean saveFlowDefinitionContent(FlowDefinitionAO flowDefinitionAo);

    FlowDefinitionAO queryFlowDefinitionInfo(Long flowDefinitionId);

    FlowDefinitionAO queryFlowDefinitionByKey(String flowKey);

    List<FlowDefinitionInfoVO> queryFlowDefinitionList(FlowDefinitionInfoQueryVO flowDefinitionInfoQueryVo);

}
