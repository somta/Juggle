package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.interfaces.param.FlowPageParam;

import java.util.List;

public interface FlowMapper extends IBaseMapper {



    FlowInfoPO querySimpleFlowInfo(Long flowId);

    /**
     * 根据流程key查询流程信息
     * @param flowKey
     * @return
     */
    FlowInfoPO queryFlowByFlowKey(String flowKey);

    /**
     * 查询流程列表
     * @param flowPageParam
     * @return
     */
    List<FlowInfoPO> queryFlowList(FlowPageParam flowPageParam);


}
