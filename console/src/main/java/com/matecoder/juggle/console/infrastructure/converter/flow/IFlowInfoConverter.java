package com.matecoder.juggle.console.infrastructure.converter.flow;

import com.matecoder.juggle.console.domain.flow.flowinfo.FlowInfoAO;
import com.matecoder.juggle.console.domain.flow.flowinfo.vo.FlowInfoVO;
import com.matecoder.juggle.console.infrastructure.po.flow.FlowInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IFlowInfoConverter {
    IFlowInfoConverter IMPL = Mappers.getMapper(IFlowInfoConverter.class);

    FlowInfoPO aoToPo(FlowInfoAO flowInfoAo);

    FlowInfoAO poToAo(FlowInfoPO flowInfoPo);

    List<FlowInfoVO> poListToVoList(List<FlowInfoPO> flowInfoList);
}
