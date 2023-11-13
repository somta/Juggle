package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.domain.definition.FlowDefinitionAO;
import net.somta.juggle.console.helper.FlowDefinitionHelper;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
class FlowDefinitionServiceImplTest {


    //todo flowRuntimeServiceImpl里面的IFlowResultManager bean没有注入
    @InjectMocks
    @Spy
    private FlowRuntimeServiceImpl flowRuntimeServiceImpl;

    @Test
    void debugFlow() {

        FlowDefinitionAO flowDefinitionAo = new FlowDefinitionAO();
        flowDefinitionAo.setFlowKey("flow_123");
        flowDefinitionAo.setFlowName("单侧流程");
        flowDefinitionAo.setFlowType("sync");
        flowDefinitionAo.setFlowContent(FlowDefinitionHelper.getFlowDefinitionContent());
        flowDefinitionAo.setRemark("这是一个单侧测试流程");
        flowDefinitionAo.setParameterEntity(FlowDefinitionHelper.getFlowDefinitionParameterEntity());
        flowDefinitionAo.setVariableInfoList(FlowDefinitionHelper.getFlowDefinitionVariableInfoList());

        TriggerDataParam triggerData = new TriggerDataParam();
        Map<String,Object> flowData = new HashMap<>(8);
        flowData.put("id",1);
        flowData.put("name","明天的地平线");
        triggerData.setFlowData(flowData);


        Flow flow = new Flow();
        flow.setFlowKey(flowDefinitionAo.getFlowKey());
        flow.setFlowName(flowDefinitionAo.getFlowName());
        flow.setFlowContent(flowDefinitionAo.getFlowContent());
        flow.setInputParams(flowDefinitionAo.getParameterEntity().getFlowRuntimeInputParameters());
        flow.setOutputParams(flowDefinitionAo.getParameterEntity().getFlowRuntimeOutputParameters());
        flow.setVariables(flowDefinitionAo.getFlowRuntimeVariables());

        FlowResult flowResult = flowRuntimeServiceImpl.triggerFlow(flow, flowDefinitionAo.getFlowType(),triggerData);
        System.out.println(flowResult.getData());

    }
}