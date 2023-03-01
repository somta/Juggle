package net.somta.juggle.console.service.impl;

import net.somta.juggle.console.model.TriggerData;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.DataTypeInfo;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.console.service.IFlowService;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.DefaultDispatcher;
import net.somta.juggle.core.model.Variable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlowServiceImpl implements IFlowService {

    private final IDispatcher dispatcher = new DefaultDispatcher();

    @Override
    public Boolean triggerFlow(FlowDefinition workflowDefinition,List<Variable> variables, TriggerData triggerData) {
        dispatcher.send(workflowDefinition,variables,triggerData.getFlowData());
        return null;
    }
}
