package net.somta.juggle.console.application.service;

import net.somta.core.base.IBaseService;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;

/**
 * @author Gavin
 */
public interface IFlowInfoService extends IBaseService<FlowInfoPO> {
    /**
     * 触发流程
     * @param flowInfoPO
     * @return
     */
   FlowResult triggerFlow(FlowInfoPO flowInfoPO, TriggerDataParam triggerData);

    /**
     * 根据流程Key获取流程信息
     * @param flowKey
     * @return
     */
    FlowInfoPO getFlowByFlowKey(String flowKey);

}
