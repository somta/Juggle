package net.somta.juggle.console.application.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.flow.enums.FlowTypeEnum;
import net.somta.juggle.console.infrastructure.mapper.FlowMapper;
import net.somta.juggle.console.infrastructure.model.FlowDefinitionInfo;
import net.somta.juggle.console.infrastructure.model.FlowInfo;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.dispatcher.impl.SyncDispatcher;
import net.somta.juggle.core.model.*;
import net.somta.juggle.console.application.service.IFlowService;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.AsyncDispatcher;
import net.somta.juggle.core.result.IFlowResultManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FlowServiceImpl extends BaseServiceImpl<FlowInfo> implements IFlowService {

    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private IFlowResultManager flowResultManager;

    private final IDispatcher dispatcher = new AsyncDispatcher();

    @Override
    public IBaseMapper getMapper() {
        return flowMapper;
    }

    @Override
    public FlowResult triggerFlow(FlowInfo flowInfo, TriggerDataParam triggerData) {
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

        FlowResult flowResult = null;
        if(FlowTypeEnum.ASYNC.getCode().equals(flowInfo.getFlowType())){
            flowResult = dispatcher.doDispatcher(flow,triggerData.getFlowData(),flowResultManager);
        }else{
            IDispatcher dispatcher = new SyncDispatcher();
            flowResult = dispatcher.doDispatcher(flow,triggerData.getFlowData(),flowResultManager);
        }
        flowResult.setFlowInstanceId(flowInstanceId);
        return flowResult;
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
