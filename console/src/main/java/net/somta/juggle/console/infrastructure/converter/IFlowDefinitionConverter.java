package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.definition.FlowDefinitionAO;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author husong
 */
@Mapper
public interface IFlowDefinitionConverter {

    IFlowDefinitionConverter IMPL = Mappers.getMapper(IFlowDefinitionConverter.class);

    FlowDefinitionInfoPO aoToPo(FlowDefinitionAO flowDefinitionAo);

    FlowDefinitionAO poToAo(FlowDefinitionInfoPO flowDefinitionInfoPo);
}
