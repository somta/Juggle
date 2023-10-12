package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.flow.FlowAO;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.infrastructure.po.FlowVersionPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IFlowVersionConverter {
    IFlowVersionConverter IMPL = Mappers.getMapper(IFlowVersionConverter.class);

    FlowVersionPO aoToPo(FlowAO flowAO);
}
