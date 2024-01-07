package net.somta.juggle.console.infrastructure.converter.flow;

import net.somta.juggle.console.domain.flow.template.FlowTemplateAO;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateInfoVO;
import net.somta.juggle.console.infrastructure.po.flow.FlowTemplatePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IFlowTemplateConverter {
    IFlowTemplateConverter IMPL = Mappers.getMapper(IFlowTemplateConverter.class);

    List<FlowTemplateInfoVO> poListToVoList(List<FlowTemplatePO> templateList);

    FlowTemplateAO poToAo(FlowTemplatePO flowTemplatePo);
}
