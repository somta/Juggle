package net.somta.juggle.console.service;

import net.somta.juggle.core.model.FlowDefinition;

public interface IFlowService {
    /**
     * 启动流程
     * @param flowDefinition
     * @return
     */
    Boolean startFlow(FlowDefinition flowDefinition);
}
