package net.somta.juggle.starter.impl;

import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.client.JuggleClient;
import net.somta.juggle.client.JuggleConfig;
import net.somta.juggle.client.JuggleFactory;
import net.somta.juggle.client.model.FlowResultModel;
import net.somta.juggle.client.model.FlowTriggerDataParam;
import net.somta.juggle.starter.properties.JuggleOpenProperties;

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
