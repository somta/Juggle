package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.model.FlowInfo;
import net.somta.juggle.console.interfaces.param.FlowPageParam;

import java.util.List;

public interface FlowMapper extends IBaseMapper {



    FlowInfo querySimpleFlowInfo(Long flowId);

    /**
     * 根据流程key查询流程信息
     * @param flowKey
     * @return
     */
    FlowInfo queryFlowByFlowKey(String flowKey);

    /**
     * 查询流程列表
     * @param flowPageParam
     * @return
     */
    List<FlowInfo> queryFlowList(FlowPageParam flowPageParam);


}
