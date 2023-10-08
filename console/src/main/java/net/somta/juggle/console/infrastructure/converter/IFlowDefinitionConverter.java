package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.definition.FlowDefinitionAO;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IFlowDefinitionConverter {

    IFlowDefinitionConverter IMPL = Mappers.getMapper(IFlowDefinitionConverter.class);

    FlowDefinitionInfoPO aoToPo(FlowDefinitionAO flowDefinitionAO);

    FlowDefinitionAO poToAo(FlowDefinitionInfoPO flowDefinitionInfoPO);
}
