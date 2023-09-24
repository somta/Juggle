package net.somta.juggle.console.application.service;

import net.somta.core.base.IBaseService;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.interfaces.param.FlowDefinitionPageParam;
import net.somta.juggle.console.interfaces.param.FlowDefinitionParam;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.core.model.FlowResult;

import java.util.List;

public interface IFlowDefinitionService extends IBaseService<FlowDefinitionInfoPO> {

    /**
     * 创建流程
     * @param flowDefinitionParam
     * @return
     */
    Boolean addFlowDefinition(FlowDefinitionParam flowDefinitionParam);

    /**
     * 删除流程
     * @param flowDefinitionId
     * @return
     */
    Boolean deleteFlowDefinition(Long flowDefinitionId);

    /**
     * 修改流程定义
     * @param flowDefinitionParam
     * @return
     */
    Boolean updateFlowDefinition(FlowDefinitionParam flowDefinitionParam);

    /**
     *
     * @param flowDefinitionId
     * @return
     */
    FlowDefinitionInfoPO getFlowDefinitionById(Long flowDefinitionId);

    /**
     * 获取流程定义
     * @param flowKey
     * @return
     */
    FlowDefinitionInfoPO getFlowDefinitionByKey(String flowKey);

    /**
     * 获取流程定义
     * @param flowKey
     * @return
     */
    FlowDefinition getFlowDefinitionByKey2(String flowKey);

    /**
     * 查询流程列表
     * @param flowDefinitionPageParam
     * @return
     */
    List<FlowDefinitionInfoPO> getFlowDefinitionList(FlowDefinitionPageParam flowDefinitionPageParam);

    /**
     * 部署流程
     * @param flowDefinitionInfoPO
     * @return
     */
    Boolean deployFlowDefinition(FlowDefinitionInfoPO flowDefinitionInfoPO);

    /**
     * @param flowDefinitionInfoPO
     * @param triggerData
     * @return
     */
    FlowResult debugFlow(FlowDefinitionInfoPO flowDefinitionInfoPO, TriggerDataParam triggerData);
}
