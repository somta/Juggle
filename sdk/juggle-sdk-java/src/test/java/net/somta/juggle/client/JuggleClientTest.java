package net.somta.juggle.client;

import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.client.model.FlowResultModel;
import net.somta.juggle.client.model.FlowTriggerDataParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JuggleClientTest {

    public static void main(String[] args) throws IOException {
        //1.实例化Juggle客户端
        JuggleConfig juggleConfig = new JuggleConfig();
        juggleConfig.setServerAddr("https://demo.juggle.plus");
        juggleConfig.setAccessToken("eyJ1c2VySWQiOjEsInRpbWVzdGFtcCI6MTcyOTAwODYzOTc5MH0=");
        JuggleClient juggleClient = JuggleFactory.getClientInstance(juggleConfig);

        //2.组装流程参数
        FlowTriggerDataParam flowTriggerDataParam = new FlowTriggerDataParam();
        Map<String,Object> data = new HashMap<>();
        data.put("userName","juggle");
        data.put("password","123456");
        data.put("deposit","1000");
        flowTriggerDataParam.setFlowData(data);

        //3.触发流程
        ResponseDataResult<FlowResultModel> result = juggleClient.triggerFlow("v1","sync_example",flowTriggerDataParam);
        FlowResultModel flowResultModel = result.getResult();
        System.out.println(flowResultModel.getStatus());
        System.out.println(flowResultModel.getData());

    }
}
