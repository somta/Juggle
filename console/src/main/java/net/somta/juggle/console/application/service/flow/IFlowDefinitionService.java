package net.somta.juggle.console.application.service.flow;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.domain.flow.definition.FlowDefinitionAO;
import net.somta.juggle.common.param.TriggerDataParam;
import net.somta.juggle.console.interfaces.param.flow.definition.*;
import net.somta.juggle.core.model.FlowResult;

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

    /**
     *
     * @param flowDefinitionContentParam
     * @return
     */
    Boolean saveFlowDefinitionContent(FlowDefinitionContentParam flowDefinitionContentParam);

    /**
     *
     * @param flowDefinitionId
     * @return
     */
    FlowDefinitionAO getFlowDefinitionInfo(Long flowDefinitionId);

    /**
     * 获取流程定义
     * @param flowKey
     * @return
     */
    FlowDefinitionAO getFlowDefinitionByKey(String flowKey);

    /**
     * Get a paginated list of flow definitions
     * @param flowDefinitionPageParam
     * @return Flow definition pagination data
     */
    PageInfo getFlowDefinitionPageList(FlowDefinitionPageParam flowDefinitionPageParam);

    /**
     * Create a flow definition based on the templat
     * @param flowDefinitionAo Flow Definition AO Object
     * @return Flow definition id
     */
    Long createFlowDefinitionByTemplate(FlowDefinitionAO flowDefinitionAo);

    /**
     * Deploy a flow
     * @param flowDefinitionDeployParam Deployment process defined input object
     * @param flowDefinitionAo Flow Definition AO Object
     * @return The results of the deployment flow
     */
    Boolean deployFlowDefinition(FlowDefinitionDeployParam flowDefinitionDeployParam, FlowDefinitionAO flowDefinitionAo);

    /**
     * Debugging a flow
     * @param flowDefinitionAo Flow Definition AO Object
     * @param triggerData Trigger flow data
     * @return Flow result data
     */
    FlowResult debugFlow(FlowDefinitionAO flowDefinitionAo, TriggerDataParam triggerData);



}
