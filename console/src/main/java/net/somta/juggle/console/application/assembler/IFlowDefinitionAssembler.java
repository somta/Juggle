package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.definition.vo.FlowDefinitionInfoQueryVO;
import net.somta.juggle.console.domain.definition.vo.FlowDefinitionInfoVO;
import net.somta.juggle.console.interfaces.dto.FlowDefinitionInfoDTO;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionAddParam;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionContentParam;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionPageParam;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IFlowDefinitionAssembler {
    IFlowDefinitionAssembler IMPL = Mappers.getMapper(IFlowDefinitionAssembler.class);

    FlowDefinitionAO paramToAo(FlowDefinitionAddParam flowDefinitionAddParam);

    FlowDefinitionAO paramToAo(FlowDefinitionUpdateParam flowDefinitionUpdateParam);

    FlowDefinitionAO paramToAo(FlowDefinitionContentParam flowDefinitionContentParam);

    @Mapping(target = "flowInputParams", expression = "java(flowDefinitionAo.getParameterEntity().getInputParameterList())")
    @Mapping(target = "flowOutputParams", expression = "java(flowDefinitionAo.getParameterEntity().getOutputParameterList())")
    FlowDefinitionInfoDTO aoToDto(FlowDefinitionAO flowDefinitionAo);

    List<FlowDefinitionInfoDTO> voListToDtoList(List<FlowDefinitionInfoVO> flowDefinitionList);

    FlowDefinitionInfoQueryVO paramToVo(FlowDefinitionPageParam flowDefinitionPageParam);
}
