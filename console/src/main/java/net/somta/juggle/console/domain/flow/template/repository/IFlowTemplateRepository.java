package net.somta.juggle.console.domain.flow.template.repository;

import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateInfoVO;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateQueryVO;

import java.util.List;

/**
 * @author Gavin
 */
public interface IFlowTemplateRepository {
    List<FlowTemplateInfoVO> queryFlowTemplateList(FlowTemplateQueryVO flowTemplateQueryVO);
}
