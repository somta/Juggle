package net.somta.juggle.console.domain.definition.repository;

import net.somta.juggle.console.domain.definition.FlowDefinitionAO;

/**
 * @author husong
 */
public interface IFlowDefinitionRepository {
    Boolean addFlowDefinition(FlowDefinitionAO flowDefinitionAO);

    Boolean deleteFlowDefinitionById(Long flowDefinitionId);

    Boolean updateFlowDefinition(FlowDefinitionAO flowDefinitionAO);

    Boolean saveFlowDefinitionContent(FlowDefinitionAO flowDefinitionAO);

    FlowDefinitionAO queryFlowDefinitionInfo(Long flowDefinitionId);
}
