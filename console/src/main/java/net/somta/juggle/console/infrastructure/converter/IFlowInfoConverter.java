package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.flow.FlowInfoAO;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IFlowInfoConverter {
    IFlowInfoConverter IMPL = Mappers.getMapper(IFlowInfoConverter.class);

    FlowInfoPO aoToPo(FlowInfoAO flowInfoAO);
}
