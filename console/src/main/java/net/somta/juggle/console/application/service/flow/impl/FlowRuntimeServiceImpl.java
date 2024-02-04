package net.somta.juggle.console.application.service.flow.impl;

import net.somta.juggle.console.application.service.flow.IFlowRuntimeService;
import net.somta.juggle.console.domain.flow.flowinfo.enums.FlowTypeEnum;
import net.somta.juggle.common.param.TriggerDataParam;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.AsyncDispatcher;
import net.somta.juggle.core.dispatcher.impl.SyncDispatcher;
import net.somta.juggle.core.model.*;
import net.somta.juggle.core.result.IFlowResultManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Gavin
 */
@Service
public class FlowRuntimeServiceImpl implements IFlowRuntimeService {

    private final IFlowResultManager flowResultManager;

    private final IDispatcher dispatcher = new AsyncDispatcher();

    public FlowRuntimeServiceImpl(IFlowResultManager flowResultManager) {
        this.flowResultManager = flowResultManager;
    }

    @Override
    public FlowResult triggerFlow(Flow flow, String flowType, TriggerDataParam triggerData) {
        String flowInstanceId = flowType + "_" + RandomStringUtils.random(10, true, true);
        flow.setFlowInstanceId(flowInstanceId);

        FlowResult flowResult = null;
        if(FlowTypeEnum.ASYNC.getCode().equals(flowType)){
            flowResult = dispatcher.doDispatcher(flow,triggerData.getFlowData(),flowResultManager);
        }else{
            IDispatcher dispatcher = new SyncDispatcher();
            flowResult = dispatcher.doDispatcher(flow,triggerData.getFlowData(),flowResultManager);
        }
        flowResult.setFlowInstanceId(flowInstanceId);
        return flowResult;
    }


    @Override
    public Map<String, Object> getAsyncFlowResult(String flowInstanceId) {
        return flowResultManager.getFlowResult(flowInstanceId);
    }
}
