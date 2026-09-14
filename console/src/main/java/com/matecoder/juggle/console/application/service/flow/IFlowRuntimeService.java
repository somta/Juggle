package com.matecoder.juggle.console.application.service.flow;

import com.matecoder.juggle.common.param.TriggerDataParam;
import com.matecoder.juggle.core.model.Flow;
import com.matecoder.juggle.core.model.FlowResult;

import java.util.Map;

/**
 * @author Gavin
 */
public interface IFlowRuntimeService {
    /**
     * start flow
     * @param flow flow object
     * @param flowType The type of this flow
     * @param triggerData trigger flow data
     * @return sync flow result
     */
    FlowResult triggerFlow(Flow flow, String flowType, TriggerDataParam triggerData);

    /**
     * get async flow result
     * @param flowInstanceId
     * @return async flow result
     */
    Map<String, Object> getAsyncFlowResult(String flowInstanceId);
}
