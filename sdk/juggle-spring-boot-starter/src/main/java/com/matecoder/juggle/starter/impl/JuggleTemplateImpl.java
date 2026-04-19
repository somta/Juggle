package com.matecoder.juggle.starter.impl;

import com.matecoder.core.protocol.ResponseDataResult;
import com.matecoder.juggle.starter.properties.JuggleOpenProperties;
import com.matecoder.juggle.client.JuggleClient;
import com.matecoder.juggle.client.JuggleConfig;
import com.matecoder.juggle.client.JuggleFactory;
import com.matecoder.juggle.client.model.FlowResultModel;
import com.matecoder.juggle.client.model.FlowTriggerDataParam;

import java.io.IOException;
import java.util.Map;

/**
 * @author husong
 */
public class JuggleTemplateImpl implements IJuggleTemplate{


    private final JuggleClient juggleClient;
    public JuggleTemplateImpl(JuggleOpenProperties juggleOpenProperties) {
        this.juggleClient = initJuggleClient(juggleOpenProperties);
    }

    @Override
    public ResponseDataResult<FlowResultModel> triggerFlow(String flowVersion, String flowKey, FlowTriggerDataParam triggerData) throws IOException {
        return juggleClient.triggerFlow(flowVersion,flowKey,triggerData);
    }

    @Override
    public ResponseDataResult<Map<String, Object>> getAsyncFlowResult(String flowInstanceId) throws IOException {
        return juggleClient.getAsyncFlowResult(flowInstanceId);
    }

    private JuggleClient initJuggleClient(JuggleOpenProperties juggleOpenProperties){
        JuggleConfig juggleConfig = new JuggleConfig();
        juggleConfig.setServerAddr(juggleOpenProperties.getServerAddr());
        juggleConfig.setAccessToken(juggleOpenProperties.getAccessToken());
        return JuggleFactory.getClientInstance(juggleConfig);
    }

}
