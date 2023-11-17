package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.helper.FlowDefinitionHelper;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowResult;
import net.somta.juggle.core.result.MemoryFlowResultManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class FlowRuntimeServiceImplTest {

    @Mock
    private MemoryFlowResultManager flowResultManager;
    @InjectMocks
    @Spy
    private FlowRuntimeServiceImpl flowRuntimeService;

    @Test
    void triggerFlow() {
        Flow flow = new Flow();
        flow.setFlowKey("flow_123");
        flow.setFlowName("单侧流程");
        flow.setFlowContent(FlowDefinitionHelper.getFlowDefinitionContent());
        flow.setInputParams(FlowDefinitionHelper.getFlowDefinitionParameterEntity().getFlowRuntimeInputParameters());
        flow.setOutputParams(FlowDefinitionHelper.getFlowDefinitionParameterEntity().getFlowRuntimeOutputParameters());
        flow.setVariables(FlowDefinitionHelper.getFlowRuntimeVariables(FlowDefinitionHelper.getFlowDefinitionVariableInfoList()));


        TriggerDataParam triggerData = new TriggerDataParam();
        Map<String,Object> flowData = new HashMap<>(8);
        flowData.put("id",1);
        flowData.put("name","明天的地平线");
        triggerData.setFlowData(flowData);

        FlowResult flowResult = flowRuntimeService.triggerFlow(flow,"sync",triggerData);
        System.out.println(flowResult);

    }
}