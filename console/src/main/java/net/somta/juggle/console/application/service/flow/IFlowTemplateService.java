package net.somta.juggle.console.application.service.flow;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.param.flow.FlowTemplatePageParam;

/**
 * @author husong
 */
public interface IFlowTemplateService {
    Boolean deleteFlowTemplate(Long templateId);

    PageInfo getFlowTemplatePageList(FlowTemplatePageParam flowTemplatePageParam);

    Long applyFlowTemplate(Long templateId);
}
