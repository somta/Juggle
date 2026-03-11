package com.matecoder.juggle.console.infrastructure.converter.flow;

import com.matecoder.juggle.console.domain.flow.definition.FlowDefinitionAO;
import com.matecoder.juggle.console.infrastructure.po.flow.FlowDefinitionInfoPO;
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
