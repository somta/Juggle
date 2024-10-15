package net.somta.juggle.client;

import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.client.model.FlowResultModel;

import java.io.IOException;

public class JuggleClientTest {

    public static void main(String[] args) {
        JuggleConfig juggleConfig = new JuggleConfig();
        juggleConfig.setServerAddr("https://demo.juggle.plus");
        juggleConfig.setAccessToken("eyJ1c2VySWQiOjEsInRpbWVzdGFtcCI6MTcyOTAwODYzOTc5MH0=");
        JuggleClient juggleClient = JuggleFactory.getClientInstance(juggleConfig);
        try {
            ResponseDataResult<FlowResultModel> result = juggleClient.triggerFlow("v1","",null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
