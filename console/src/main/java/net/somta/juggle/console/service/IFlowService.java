package net.somta.juggle.console.service;

import net.somta.juggle.console.model.FlowInfo;
import net.somta.juggle.console.model.dto.FlowResultDTO;
import net.somta.juggle.console.model.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.core.model.Variable;

import java.util.List;
import java.util.Map;

public interface IFlowService {
    /**
     * 启动流程
     * @param flowInfo
     * @return
     */
    FlowResultDTO triggerFlow(FlowInfo flowInfo, TriggerDataParam triggerData);

    /**
     * 根据流程Key获取流程信息
     * @param flowKey
     * @return
     */
    FlowInfo getFlowByFlowKey(String flowKey);

    Map<String, Object> getAsyncFlowResult(String flowInstanceId);
}
