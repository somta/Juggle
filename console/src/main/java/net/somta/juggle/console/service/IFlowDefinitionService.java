package net.somta.juggle.console.service;

import net.somta.juggle.core.model.FlowDefinition;

public interface IFlowDefinitionService {

    /**
     * 获取流程定义
     * @param flowKey
     * @return
     */
    FlowDefinition getFlowDefinitionByKey(String flowKey);
}