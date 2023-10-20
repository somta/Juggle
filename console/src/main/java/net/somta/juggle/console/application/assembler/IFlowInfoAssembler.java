package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.flow.vo.FlowInfoQueryVO;
import net.somta.juggle.console.domain.flow.vo.FlowInfoVO;
import net.somta.juggle.console.interfaces.dto.FlowInfoDTO;
import net.somta.juggle.console.interfaces.param.FlowInfoPageParam;
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
