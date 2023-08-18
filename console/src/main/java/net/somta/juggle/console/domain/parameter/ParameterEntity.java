package net.somta.juggle.console.domain.parameter;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.infrastructure.model.Parameter;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.InputParameter;
import net.somta.juggle.core.model.OutputParameter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ParameterEntity {

    /**
     * 获取流程运行时入参
     * @param inputParameterList
     * @return
     */
    public List<InputParameter> getFlowRuntimeInputParameters(List<Parameter> inputParameterList){
        List<InputParameter> inputParams = new ArrayList<>();
        if(CollectionUtils.isEmpty(inputParameterList)){
            return inputParams;
        }
        for (Parameter parameter : inputParameterList) {
            InputParameter inputParameter = new InputParameter();
            inputParameter.setKey(parameter.getParamKey());
            inputParameter.setName(parameter.getParamName());
            inputParameter.setDataType(JsonSerializeHelper.deserialize(parameter.getDataType(), DataType.class));
            inputParameter.setRequired(parameter.getRequired());
            inputParams.add(inputParameter);
        }
        return inputParams;
    }

    /**
     * 获取流程运行时出参
     * @param outputParameterList
     * @return
     */
    public List<OutputParameter> getFlowRuntimeOutputParameters(List<Parameter> outputParameterList){
        List<OutputParameter> outputParams = new ArrayList<>();
        if(CollectionUtils.isEmpty(outputParameterList)){
            return outputParams;
        }
        for (Parameter parameter : outputParameterList) {
            OutputParameter outputParameter = new OutputParameter();
            outputParameter.setKey(parameter.getParamKey());
            outputParameter.setName(parameter.getParamName());
            outputParameter.setDataType(JsonSerializeHelper.deserialize(parameter.getDataType(),DataType.class));
            outputParams.add(outputParameter);
        }
        return outputParams;
    }

}
