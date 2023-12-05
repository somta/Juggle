package net.somta.juggle.console.application.assembler.flow;

import net.somta.juggle.console.domain.flow.flowinfo.vo.FlowInfoQueryVO;
import net.somta.juggle.console.domain.flow.flowinfo.vo.FlowInfoVO;
import net.somta.juggle.console.interfaces.dto.flow.FlowInfoDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowInfoPageParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IFlowInfoAssembler {
    IFlowInfoAssembler IMPL = Mappers.getMapper(IFlowInfoAssembler.class);

    FlowInfoQueryVO paramToVo(FlowInfoPageParam flowInfoPageParam);

    List<FlowInfoDTO> voListToDtoList(List<FlowInfoVO> flowInfoList);
}
