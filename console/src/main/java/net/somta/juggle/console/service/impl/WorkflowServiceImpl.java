package net.somta.juggle.console.service.impl;

import net.somta.juggle.core.model.WorkflowDefinition;
import net.somta.juggle.console.service.IWorkflowService;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.DefaultDispatcher;
import net.somta.juggle.core.model.Variable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkflowServiceImpl implements IWorkflowService {
    @Override
    public Boolean startFlow(WorkflowDefinition workflowDefinition) {
        IDispatcher dispatcher = new DefaultDispatcher();
        List<Variable> variables = new ArrayList<>();

        dispatcher.send(workflowDefinition,variables);
        return null;
    }
}
