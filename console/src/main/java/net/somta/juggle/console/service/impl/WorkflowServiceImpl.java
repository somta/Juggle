package net.somta.juggle.console.service.impl;

import net.somta.juggle.console.model.WorkflowDefinition;
import net.somta.juggle.console.service.IWorkflowService;
import org.springframework.stereotype.Service;

@Service
public class WorkflowServiceImpl implements IWorkflowService {
    @Override
    public Boolean startFlow(WorkflowDefinition workflowDefinition) {
        return null;
    }
}
