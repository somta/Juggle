package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.domain.definition.FlowDefinitionAO;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionContentParam;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionPageParam;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionAddParam;
import net.somta.juggle.console.interfaces.param.definition.FlowDefinitionUpdateParam;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.core.model.FlowResult;

import java.util.List;

/**
 * @author husong
 */
public interface IFlowDefinitionService {

    /**
     * 创建流程
     * @param flowDefinitionAddParam
     * @return
     */
    Boolean addFlowDefinition(FlowDefinitionAddParam flowDefinitionAddParam);

    /**
     * 删除流程
     * @param flowDefinitionId
     * @return
     */
    Boolean deleteFlowDefinition(Long flowDefinitionId);

    /**
     * 修改流程定义
     * @param flowDefinitionUpdateParam
     * @return
     */
    Boolean updateFlowDefinition(FlowDefinitionUpdateParam flowDefinitionUpdateParam);

    Boolean saveFlowDefinitionContent(FlowDefinitionContentParam flowDefinitionContentParam);

    FlowDefinitionAO getFlowDefinitionInfo(Long flowDefinitionId);

    /**
     * 获取流程定义
     * @param flowKey
     * @return
     */
    FlowDefinitionAO getFlowDefinitionByKey(String flowKey);

    PageInfo getFlowDefinitionPageList(FlowDefinitionPageParam flowDefinitionPageParam);

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
     * @param flowDefinitionAo
     * @return
     */
    Boolean deployFlowDefinition(String flowVersion, FlowDefinitionAO flowDefinitionAo);

    /**
     * @param flowDefinitionAo
     * @param triggerData
     * @return
     */
    FlowResult debugFlow(FlowDefinitionAO flowDefinitionAo, TriggerDataParam triggerData);


}
