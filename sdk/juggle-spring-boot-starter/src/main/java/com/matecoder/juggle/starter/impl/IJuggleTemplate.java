package com.matecoder.juggle.starter.impl;

import com.matecoder.core.protocol.ResponseDataResult;
import com.matecoder.juggle.client.model.FlowResultModel;
import com.matecoder.juggle.client.model.FlowTriggerDataParam;

import java.io.IOException;
import java.util.Map;

/**
 * @author husong
 */
public interface IJuggleTemplate {

    ResponseDataResult<FlowResultModel> triggerFlow(String flowVersion, String flowKey, FlowTriggerDataParam triggerData) throws IOException;

    ResponseDataResult<Map<String,Object>> getAsyncFlowResult(String flowInstanceId) throws IOException;
}
