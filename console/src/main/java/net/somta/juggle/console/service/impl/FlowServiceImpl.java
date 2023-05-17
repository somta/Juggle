package net.somta.juggle.console.service.impl;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.mapper.FlowMapper;
import net.somta.juggle.console.model.FlowInfo;
import net.somta.juggle.console.model.dto.FlowResultDTO;
import net.somta.juggle.console.model.param.TriggerDataParam;
import net.somta.juggle.core.model.*;
import net.somta.juggle.console.service.IFlowService;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.DefaultDispatcher;
import net.somta.juggle.core.result.IFlowResultManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FlowServiceImpl implements IFlowService {

    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private IFlowResultManager flowResultManager;

    private final IDispatcher dispatcher = new DefaultDispatcher();

    @Override
    public FlowResultDTO triggerFlow(FlowInfo flowInfo, TriggerDataParam triggerData) {
        FlowResultDTO flowResultDTO = new FlowResultDTO();
        String flowInstanceId = flowInfo.getFlowType() + "_" + RandomStringUtils.random(10, true, true);;
        Flow flow = new Flow();
        flow.setFlowInstanceId(flowInstanceId);
        flow.setFlowKey(flowInfo.getFlowKey());
        flow.setFlowName(flowInfo.getFlowName());
        flow.setFlowContent(flowInfo.getFlowContent());

        String inputParameters = flowInfo.getInputs();
        if(StringUtils.isNotEmpty(inputParameters)){
            List<InputParameter> inputParams = JsonSerializeHelper.deserialize(inputParameters,List.class,InputParameter.class);
            flow.setInputParams(inputParams);
        }

        String outputParameters = flowInfo.getOutputs();
        if(StringUtils.isNotEmpty(outputParameters)){
            List<OutputParameter> outputParams = JsonSerializeHelper.deserialize(outputParameters,List.class,OutputParameter.class);
            flow.setOutputParams(outputParams);
        }

        String variables = flowInfo.getVariables();
        if(StringUtils.isNotEmpty(variables)){
            List<Variable> variableList = JsonSerializeHelper.deserialize(variables,List.class,Variable.class);
            flow.setVariables(variableList);
        }

        dispatcher.doDispatcher(flow,triggerData.getFlowData(),flowResultManager);

        flowResultDTO.setFlowInstanceId(flowInstanceId);
        return flowResultDTO;
    }

    @Override
    public FlowInfo getFlowByFlowKey(String flowKey) {
        return flowMapper.queryFlowByFlowKey(flowKey);
    }

    @Override
    public Map<String, Object> getAsyncFlowResult(String flowInstanceId) {
        return flowResultManager.getFlowResult(flowInstanceId);
    }


}
