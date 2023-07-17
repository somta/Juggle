package net.somta.juggle.console.application.service;

import net.somta.core.base.IBaseService;
import net.somta.juggle.console.infrastructure.model.FlowDefinitionInfo;
import net.somta.juggle.console.infrastructure.model.FlowInfo;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;

import java.util.Map;

/**
 * @author Gavin
 */
public interface IFlowService extends IBaseService<FlowInfo> {
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
