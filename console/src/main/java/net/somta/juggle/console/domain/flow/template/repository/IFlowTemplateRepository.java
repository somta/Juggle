package net.somta.juggle.console.domain.flow.template.repository;

import net.somta.juggle.console.domain.flow.template.FlowTemplateAO;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateInfoVO;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateQueryVO;

import java.util.List;

/**
 * @author Gavin
 */
public interface IFlowTemplateRepository {
    /**
     * Delete template based on template ID
     * @param templateId template id
     * @return Boolean
     */
    Boolean deleteFlowTemplateById(Long templateId);

    FlowTemplateAO queryFlowTemplateId(Long templateId);

    /**
     * Query flow list
     * @param flowTemplateQueryVO Template Query VO
     * @return Basic template information list
     */
    List<FlowTemplateInfoVO> queryFlowTemplateList(FlowTemplateQueryVO flowTemplateQueryVO);


}
