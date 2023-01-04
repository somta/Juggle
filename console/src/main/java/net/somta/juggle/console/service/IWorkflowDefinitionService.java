package net.somta.juggle.console.service;

import net.somta.juggle.console.model.WorkflowDefinition;

public interface IWorkflowDefinitionService {

    /**
     * 获取流程定义
     * @param flowKey
     * @return
     */
    WorkflowDefinition getFlowDefinitionByKey(String flowKey);
}
