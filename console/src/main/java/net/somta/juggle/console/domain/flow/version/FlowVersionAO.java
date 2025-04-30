package net.somta.juggle.console.domain.flow.version;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.flow.version.enums.FlowVersionStatusEnum;
import net.somta.juggle.core.model.InputParameter;
import net.somta.juggle.core.model.OutputParameter;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author husong
 */
public class FlowVersionAO {
    private Long id;

    private Long flowId;

    private String flowKey;

    private String flowName;

    private String flowType;

    private String flowVersion;

    private FlowVersionStatusEnum flowVersionStatusEnum;

    private String flowContent;

    private String inputs;

    private String outputs;

    private String variables;

    public void setNegateStatus(FlowVersionStatusEnum flowVersionStatus){
        if(FlowVersionStatusEnum.DISABLED == flowVersionStatus){
            this.flowVersionStatusEnum = FlowVersionStatusEnum.ENABLE;
        } else {
            this.flowVersionStatusEnum = FlowVersionStatusEnum.DISABLED;
        }
    }

    public List<InputParameter> getFlowRuntimeInputParameters() {
        List<InputParameter> inputParameterList = null;
        try {
            if(StringUtils.isNotBlank(this.inputs)){
                inputParameterList = JsonSerializeHelper.deserialize(this.inputs, List.class, InputParameter.class);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return inputParameterList;
    }

    public List<OutputParameter> getFlowRuntimeOutputParameters() {
        List<OutputParameter> outputParameterList = null;
        try {
            outputParameterList = JsonSerializeHelper.deserialize(this.outputs, List.class, OutputParameter.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return outputParameterList;
    }

    public List<Variable> getFlowRuntimeVariables() {
        List<Variable> variableList = null;
        try {
            variableList = JsonSerializeHelper.deserialize(this.variables, List.class, Variable.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return variableList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getFlowVersion() {
        return flowVersion;
    }

    public void setFlowVersion(String flowVersion) {
        this.flowVersion = flowVersion;
    }

    public FlowVersionStatusEnum getFlowVersionStatusEnum() {
        return flowVersionStatusEnum;
    }

    public void setFlowVersionStatusEnum(FlowVersionStatusEnum flowVersionStatusEnum) {
        this.flowVersionStatusEnum = flowVersionStatusEnum;
    }

    public String getFlowContent() {
        return flowContent;
    }

    public void setFlowContent(String flowContent) {
        this.flowContent = flowContent;
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public String getOutputs() {
        return outputs;
    }

    public void setOutputs(String outputs) {
        this.outputs = outputs;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }



}
