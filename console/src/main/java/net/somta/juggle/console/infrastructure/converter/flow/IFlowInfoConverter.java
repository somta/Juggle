package net.somta.juggle.console.infrastructure.converter.flow;

import net.somta.juggle.console.domain.flow.flowinfo.FlowInfoAO;
import net.somta.juggle.console.domain.flow.flowinfo.vo.FlowInfoVO;
import net.somta.juggle.console.infrastructure.po.flow.FlowInfoPO;
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
