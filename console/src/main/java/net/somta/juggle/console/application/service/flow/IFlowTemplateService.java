package net.somta.juggle.console.application.service.flow;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.param.flow.FlowTemplatePageParam;

/**
 * @author husong
 */
public interface IFlowTemplateService {
    PageInfo getFlowTemplatePageList(FlowTemplatePageParam flowTemplatePageParam);
}
