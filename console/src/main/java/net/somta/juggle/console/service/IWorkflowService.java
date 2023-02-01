package net.somta.juggle.console.service;

import net.somta.juggle.core.model.FlowDefinition;

public interface IWorkflowService {
    /**
     * 启动流程
     * @param workflowDefinition
     * @return
     */
    Boolean startFlow(FlowDefinition workflowDefinition);
}
