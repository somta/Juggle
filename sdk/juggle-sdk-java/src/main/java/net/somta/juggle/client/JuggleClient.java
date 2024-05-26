package net.somta.juggle.client;

import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.client.model.FlowResultModel;
import net.somta.juggle.client.model.FlowTriggerDataParam;

import java.io.IOException;
import java.util.Map;

/**
 * @author husong
 * @since 1.2.0
 */
public interface JuggleClient {

    /**
     * Trigger a sync flow
     * @param flowVersion flow version number
     * @param flowKey flow key
     * @param triggerData trigger flow data
     * @return return flow result data
     * @throws IOException
     */
    ResponseDataResult<FlowResultModel> triggerFlow(String flowVersion, String flowKey, FlowTriggerDataParam triggerData) throws IOException;

    /**
     * get a async flow data
     * @param flowInstanceId flow instance id
     * @return return async flow result data
     * @throws IOException
     */
    ResponseDataResult<Map<String,Object>> getAsyncFlowResult(String flowInstanceId) throws IOException;
}
