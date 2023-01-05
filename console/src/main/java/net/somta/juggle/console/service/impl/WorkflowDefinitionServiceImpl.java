package net.somta.juggle.console.service.impl;

import net.somta.juggle.core.model.WorkflowDefinition;
import net.somta.juggle.console.service.IWorkflowDefinitionService;
import org.springframework.stereotype.Service;

@Service
public class WorkflowDefinitionServiceImpl implements IWorkflowDefinitionService {
    @Override
    public WorkflowDefinition getFlowDefinitionByKey(String flowKey) {
        WorkflowDefinition workflowDefinition = new WorkflowDefinition();
        workflowDefinition.setFlowKey("flow_123");
        return workflowDefinition;
    }
}
