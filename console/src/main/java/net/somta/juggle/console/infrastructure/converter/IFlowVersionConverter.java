package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.flow.FlowInfoAO;
import net.somta.juggle.console.domain.version.FlowVersionAO;
import net.somta.juggle.console.domain.version.enums.FlowVersionStatusEnum;
import net.somta.juggle.console.domain.version.vo.FlowVersionVO;
import net.somta.juggle.console.infrastructure.po.FlowVersionPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IFlowVersionConverter {
    IFlowVersionConverter IMPL = Mappers.getMapper(IFlowVersionConverter.class);

    FlowVersionPO aoToPo(FlowInfoAO flowInfoAO);

    @Mapping(target = "flowVersionStatus", expression = "java(flowVersionAO.getFlowVersionStatusEnum().getCode())")
    FlowVersionPO aoToPo(FlowVersionAO flowVersionAO);

    @Mapping(target = "flowVersionStatusEnum", expression = "java(net.somta.juggle.console.domain.version.enums.FlowVersionStatusEnum.getByCode(flowVersionPO.getFlowVersionStatus()))")
    FlowVersionAO poToAo(FlowVersionPO flowVersionPO);

}
