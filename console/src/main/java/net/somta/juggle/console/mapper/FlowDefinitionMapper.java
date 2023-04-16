package net.somta.juggle.console.mapper;


import net.somta.juggle.console.model.FlowDefinitionInfo;
import net.somta.juggle.console.model.param.FlowDefinitionPageParam;
import net.somta.juggle.console.model.param.FlowDefinitionParam;

import java.util.List;

public interface FlowDefinitionMapper {


    /**
     * 创建流程
     * @param flowDefinition
     * @return
     */
    int addFlowDefinition(FlowDefinitionInfo flowDefinition);

    /**
     * 根据ID删除流程
     * @param id
     * @return
     */
    int deleteFlowDefinitionById(Long id);

    /**
     * 修改流程定义
     * @param flowDefinitionParam
     * @return
     */
    int updateFlowDefinitionById(FlowDefinitionParam flowDefinitionParam);

    /**
     *
     * @param id
     * @return
     */
    FlowDefinitionInfo queryFlowDefinitionById(Long id);

    /**
     * 查询流程列表
     * @param flowDefinitionPageParam
     * @return
     */
    List<FlowDefinitionInfo> queryFlowDefinitionList(FlowDefinitionPageParam flowDefinitionPageParam);



}
