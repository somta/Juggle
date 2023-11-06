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

/**
 * @author husong
 */
@Mapper
public interface IFlowVersionConverter {
    IFlowVersionConverter IMPL = Mappers.getMapper(IFlowVersionConverter.class);

    FlowVersionPO aoToPo(FlowInfoAO flowInfoAo);

    @Mapping(target = "flowVersionStatus", expression = "java(flowVersionAo.getFlowVersionStatusEnum().getCode())")
    FlowVersionPO aoToPo(FlowVersionAO flowVersionAo);

    @Mapping(target = "flowVersionStatusEnum", expression = "java(net.somta.juggle.console.domain.version.enums.FlowVersionStatusEnum.getByCode(flowVersionPo.getFlowVersionStatus()))")
    FlowVersionAO poToAo(FlowVersionPO flowVersionPo);

}
