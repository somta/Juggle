package com.matecoder.juggle.console.application.service.impl;

import com.matecoder.juggle.console.application.service.flow.impl.FlowRuntimeServiceImpl;
import com.matecoder.juggle.console.configuration.JuggleProperties;
import com.matecoder.juggle.console.domain.system.datasource.repository.IDataSourceRepository;
import com.matecoder.juggle.console.helper.FlowDefinitionHelper;
import com.matecoder.juggle.common.param.TriggerDataParam;
import com.matecoder.juggle.core.enums.FlowResultManagerTypeEnum;
import com.matecoder.juggle.core.model.Flow;
import com.matecoder.juggle.core.model.FlowResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

//todo 这里要启动springboot的环境
@ExtendWith(SpringExtension.class)
class FlowRuntimeServiceImplTest {

    private FlowRuntimeServiceImpl flowRuntimeService;
    @Mock
    private IDataSourceRepository dataSourceRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        JuggleProperties juggleProperties = new  JuggleProperties();
        juggleProperties.getCache().setCacheType(FlowResultManagerTypeEnum.MEMORY);
        flowRuntimeService = new FlowRuntimeServiceImpl(juggleProperties,dataSourceRepository);
    }

    @Test
    void triggerFlow() {
        Flow flow = new Flow();
        flow.setFlowKey("flow_123");
        flow.setFlowName("单侧流程");
        flow.setFlowContent(FlowDefinitionHelper.getFlowDefinitionContent());
        flow.setInputParams(FlowDefinitionHelper.getFlowDefinitionParameterEntity().getInputParameterSchema());
        flow.setOutputParams(FlowDefinitionHelper.getFlowDefinitionParameterEntity().getOutputParameterSchema());
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