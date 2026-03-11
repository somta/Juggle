package com.matecoder.juggle.console.infrastructure.converter.flow;

import com.matecoder.juggle.console.domain.flow.flowinfo.FlowInfoAO;
import com.matecoder.juggle.console.domain.flow.version.FlowVersionAO;
import com.matecoder.juggle.console.domain.flow.version.view.FlowVersionInfoView;
import com.matecoder.juggle.console.infrastructure.po.flow.FlowVersionPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author husong
 */
@Mapper
public interface IFlowVersionConverter {
    IFlowVersionConverter IMPL = Mappers.getMapper(IFlowVersionConverter.class);

    FlowVersionPO aoToPo(FlowInfoAO flowInfoAo);

    @Mapping(target = "flowVersionStatus", expression = "java(flowVersionAo.getFlowVersionStatusEnum().getCode())")
    FlowVersionPO aoToPo(FlowVersionAO flowVersionAo);

    @Mapping(target = "flowVersionStatusEnum", expression = "java(com.matecoder.juggle.console.domain.flow.version.enums.FlowVersionStatusEnum.getByCode(flowVersionPo.getFlowVersionStatus()))")
    FlowVersionAO poToAo(FlowVersionPO flowVersionPo);

    @Mapping(target = "flowVersionStatusEnum", expression = "java(com.matecoder.juggle.console.domain.flow.version.enums.FlowVersionStatusEnum.getByCode(flowVersionInfoView.getFlowVersionStatus()))")
    FlowVersionAO viewToAo(FlowVersionInfoView flowVersionInfoView);
}
