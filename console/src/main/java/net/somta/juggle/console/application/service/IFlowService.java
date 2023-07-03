package net.somta.juggle.console.application.service;

import net.somta.juggle.console.infrastructure.model.FlowInfo;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;

import java.util.Map;

public interface IFlowService {
    /**
     * 启动流程
     * @param flowInfo
     * @return
     */
    FlowResult triggerFlow(FlowInfo flowInfo, TriggerDataParam triggerData);

    /**
     * 根据流程Key获取流程信息
     * @param flowKey
     * @return
     */
    FlowInfo getFlowByFlowKey(String flowKey);

    Map<String, Object> getAsyncFlowResult(String flowInstanceId);
}
