package net.somta.juggle.console.application.service;

import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowResult;

import java.util.Map;

/**
 * @author Gavin
 */
public interface IFlowRuntimeService {
    /**
     * 启动流程
     * @param flow
     * @return
     */
    FlowResult triggerFlow(Flow flow, String flowType, TriggerDataParam triggerData);

    Map<String, Object> getAsyncFlowResult(String flowInstanceId);
}
