package net.somta.juggle.console.service;

import net.somta.juggle.console.model.WorkflowDefinition;

public interface IWorkflowService {
    /**
     * 启动流程
     * @param workflowDefinition
     * @return
     */
    Boolean startFlow(WorkflowDefinition workflowDefinition);
}
