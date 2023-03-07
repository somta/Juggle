package net.somta.juggle.console.service.impl;

import net.somta.juggle.console.model.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.console.service.IFlowService;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.DefaultDispatcher;
import net.somta.juggle.core.model.Variable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowServiceImpl implements IFlowService {

    private final IDispatcher dispatcher = new DefaultDispatcher();

    @Override
    public Boolean triggerFlow(FlowDefinition workflowDefinition,List<Variable> variables, TriggerDataParam triggerData) {
        dispatcher.doDispatcher(workflowDefinition,variables,triggerData.getFlowData());
        return null;
    }
}
