package net.somta.juggle.console.application.assembler.flow;

import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateInfoVO;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateQueryVO;
import net.somta.juggle.console.interfaces.dto.flow.FlowTemplateInfoDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowTemplatePageParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IFlowTemplateAssembler {
    IFlowTemplateAssembler IMPL = Mappers.getMapper(IFlowTemplateAssembler.class);

   /* FlowDefinitionAO paramToAo(FlowDefinitionAddParam flowDefinitionAddParam);

    FlowDefinitionAO paramToAo(FlowDefinitionUpdateParam flowDefinitionUpdateParam);

    FlowDefinitionAO paramToAo(FlowDefinitionContentParam flowDefinitionContentParam);

    @Mapping(target = "flowInputParams", expression = "java(flowDefinitionAo.getParameterEntity().getInputParameterList())")
    @Mapping(target = "flowOutputParams", expression = "java(flowDefinitionAo.getParameterEntity().getOutputParameterList())")
    FlowDefinitionInfoDTO aoToDto(FlowDefinitionAO flowDefinitionAo);

    List<FlowDefinitionInfoDTO> voListToDtoList(List<FlowDefinitionInfoVO> flowDefinitionList);*/

    FlowTemplateQueryVO paramToVo(FlowTemplatePageParam flowTemplatePageParam);

    List<FlowTemplateInfoDTO> voListToDtoList(List<FlowTemplateInfoVO> flowTemplateList);
}
