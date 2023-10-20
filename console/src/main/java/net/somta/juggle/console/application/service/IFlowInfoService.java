package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.param.FlowInfoPageParam;

/**
 * @author husong
 */
public interface IFlowInfoService {

    Boolean deleteFlowInfo(Long flowId);

    PageInfo getFlowInfoPageList(FlowInfoPageParam flowInfoPageParam);
}
