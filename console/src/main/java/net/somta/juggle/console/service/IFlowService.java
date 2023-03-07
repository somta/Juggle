package net.somta.juggle.console.service;

import net.somta.juggle.console.model.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.core.model.Variable;

import java.util.List;

public interface IFlowService {
    /**
     * 启动流程
     * @param flowDefinition
     * @return
     */
    Boolean triggerFlow(FlowDefinition flowDefinition, List<Variable> variables, TriggerDataParam triggerData);
}
