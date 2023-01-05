package net.somta.juggle.console.service;

import net.somta.juggle.core.model.WorkflowDefinition;

public interface IWorkflowService {
    /**
     * 启动流程
     * @param workflowDefinition
     * @return
     */
    Boolean startFlow(WorkflowDefinition workflowDefinition);
}
