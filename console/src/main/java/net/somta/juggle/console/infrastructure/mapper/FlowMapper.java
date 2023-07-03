package net.somta.juggle.console.infrastructure.mapper;


import net.somta.juggle.console.infrastructure.model.FlowInfo;
import net.somta.juggle.console.interfaces.param.FlowPageParam;

import java.util.List;

public interface FlowMapper {


    /**
     * 创建流程
     * @param flow
     * @return
     */
    int addFlow(FlowInfo flow);

    /**
     * 根据ID删除流程
     * @param flowDefinitionId
     * @return
     */
    int deleteFlowById(Long flowDefinitionId);

    /**
     * 修改流程定义
     * @param flowDefinitionParam
     * @return
     */
    //int updateFlowDefinitionById(FlowDefinitionParam flowDefinitionParam);

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
