package net.somta.juggle.starter.impl;

import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.starter.model.FlowResultModel;
import net.somta.juggle.starter.model.FlowTriggerDataParam;

import java.io.IOException;
import java.util.Map;

/**
 * @author husong
 */
public interface IJuggleTemplate {

    ResponseDataResult<FlowResultModel> triggerFlow(String flowVersion, String flowKey, FlowTriggerDataParam triggerData) throws IOException;

    ResponseDataResult<Map<String,Object>> getAsyncFlowResult(String flowInstanceId) throws IOException;
}
