package net.somta.juggle.starter.impl;

import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.common.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;

import java.io.IOException;
import java.util.Map;

/**
 * @author husong
 */
public interface IJuggleTemplate {

    ResponseDataResult<FlowResult> triggerFlow(String flowVersion, String flowKey, TriggerDataParam triggerData) throws IOException;

    ResponseDataResult<Map<String,Object>> getAsyncFlowResult(String flowInstanceId) throws IOException;
}
