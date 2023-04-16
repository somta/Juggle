package net.somta.juggle.console.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.juggle.console.mapper.FlowMapper;
import net.somta.juggle.console.model.FlowInfo;
import net.somta.juggle.console.model.param.TriggerDataParam;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.console.service.IFlowService;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.DefaultDispatcher;
import net.somta.juggle.core.model.InputParameter;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlowServiceImpl implements IFlowService {

    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private ObjectMapper objectMapper;

    private final IDispatcher dispatcher = new DefaultDispatcher();

    @Override
    public Boolean triggerFlow(FlowInfo flowInfo, TriggerDataParam triggerData) {

        Flow flow = new Flow();
        flow.setFlowKey(flowInfo.getFlowKey());
        flow.setFlowName(flowInfo.getFlowName());
        flow.setFlowContent(flowInfo.getFlowContent());

        String inputParameters = flowInfo.getInputs();
        if(StringUtils.isNotEmpty(inputParameters)){

        }

        String outputParameters = flowInfo.getOutputs();
        if(StringUtils.isNotEmpty(outputParameters)){
            List<InputParameter> inputs = new ArrayList<>();
            //todo
            flow.setInputs(inputs);
        }

        String variables = flowInfo.getVariables();
        if(StringUtils.isNotEmpty(variables)){
            //objectMapper.readValue();
        }

        //dispatcher.doDispatcher(workflowDefinition,variables,triggerData.getFlowData());
        return null;
    }

    @Override
    public FlowInfo getFlowByFlowKey(String flowKey) {
        return flowMapper.queryFlowByFlowKey(flowKey);
    }


}
