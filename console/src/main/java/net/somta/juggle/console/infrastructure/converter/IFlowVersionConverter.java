package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.flow.FlowInfoAO;
import net.somta.juggle.console.domain.version.FlowVersionAO;
import net.somta.juggle.console.domain.version.vo.FlowVersionVO;
import net.somta.juggle.console.infrastructure.po.FlowVersionPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IFlowVersionConverter {
    IFlowVersionConverter IMPL = Mappers.getMapper(IFlowVersionConverter.class);

    FlowVersionPO aoToPo(FlowInfoAO flowInfoAO);

    FlowVersionAO poToAo(FlowVersionPO flowVersionPO);
}
