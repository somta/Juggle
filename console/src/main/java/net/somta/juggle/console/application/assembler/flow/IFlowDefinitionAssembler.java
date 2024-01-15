package net.somta.juggle.console.application.assembler.flow;

import net.somta.juggle.console.domain.flow.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoQueryVO;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoVO;
import net.somta.juggle.console.interfaces.dto.flow.FlowDefinitionInfoDTO;
import net.somta.juggle.console.interfaces.param.flow.definition.FlowDefinitionAddParam;
import net.somta.juggle.console.interfaces.param.flow.definition.FlowDefinitionContentParam;
import net.somta.juggle.console.interfaces.param.flow.definition.FlowDefinitionPageParam;
import net.somta.juggle.console.interfaces.param.flow.definition.FlowDefinitionUpdateParam;
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
    @Mapping(target = "flowVariables", expression = "java(flowDefinitionAo.getVariableInfoList())")
    FlowDefinitionInfoDTO aoToDto(FlowDefinitionAO flowDefinitionAo);

    List<FlowDefinitionInfoDTO> voListToDtoList(List<FlowDefinitionInfoVO> flowDefinitionList);

    FlowDefinitionInfoQueryVO paramToVo(FlowDefinitionPageParam flowDefinitionPageParam);
}
