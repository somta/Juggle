package net.somta.juggle.console.application.service.flow;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.param.flow.FlowInfoPageParam;

/**
 * @author husong
 */
public interface IFlowInfoService {

    Boolean deleteFlowInfo(Long flowId);

    PageInfo getFlowInfoPageList(FlowInfoPageParam flowInfoPageParam);
}
