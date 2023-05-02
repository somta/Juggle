package net.somta.juggle.console.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.mapper.FlowMapper;
import net.somta.juggle.console.model.FlowInfo;
import net.somta.juggle.console.model.Parameter;
import net.somta.juggle.console.model.VariableInfo;
import net.somta.juggle.console.model.param.TriggerDataParam;
import net.somta.juggle.core.model.*;
import net.somta.juggle.console.service.IFlowService;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.DefaultDispatcher;
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
            List<InputParameter> inputParams = new ArrayList<>();
            List<Parameter> inputs = JsonSerializeHelper.deserialize(inputParameters,List.class,Parameter.class);
            //List<Parameter> inputs = objectMapper.readValue(inputParameters, new TypeReference<List<Parameter>>(){});
            for (Parameter parameter : inputs) {
                InputParameter inputParameter = new InputParameter();
                inputParameter.setKey(parameter.getParamKey());
                inputParameter.setName(parameter.getParamName());
                inputParameter.setDataType(JsonSerializeHelper.deserialize(parameter.getDataType(),DataType.class));
                inputParameter.setRequired(parameter.getRequired());
                inputParams.add(inputParameter);
            }
            flow.setInputParams(inputParams);
        }

        String outputParameters = flowInfo.getOutputs();
        if(StringUtils.isNotEmpty(outputParameters)){
            List<OutputParameter> outputParams = new ArrayList<>();
            try {
                //List<Parameter> outputs = objectMapper.readValue(outputParameters, new TypeReference<List<Parameter>>(){});
                List<Parameter> outputs = JsonSerializeHelper.deserialize(outputParameters,List.class,Parameter.class);
                for (Parameter parameter : outputs) {
                    OutputParameter outputParameter = new OutputParameter();
                    outputParameter.setKey(parameter.getParamKey());
                    outputParameter.setName(parameter.getParamName());
                    outputParameter.setDataType(JsonSerializeHelper.deserialize(parameter.getDataType(),DataType.class));
                    outputParams.add(outputParameter);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            flow.setOutputParams(outputParams);
        }

        String variables = flowInfo.getVariables();
        if(StringUtils.isNotEmpty(variables)){
            List<Variable> variableList = new ArrayList<>();
            try {
                List<VariableInfo> variableInfoList = objectMapper.readValue(variables, new TypeReference<List<VariableInfo>>(){});
                for (VariableInfo variableInfo : variableInfoList) {
                    Variable variable = new Variable();
                    variable.setKey(variableInfo.getEnvKey());
                    variable.setName(variableInfo.getEnvName());
                    variable.setDataType(JsonSerializeHelper.deserialize(variableInfo.getDataType(),DataType.class));
                    variableList.add(variable);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            flow.setVariables(variableList);

        }

        dispatcher.doDispatcher(flow,triggerData.getFlowData());
        return null;
    }

    @Override
    public FlowInfo getFlowByFlowKey(String flowKey) {
        return flowMapper.queryFlowByFlowKey(flowKey);
    }


}
