package net.somta.juggle.console.interfaces.param;

import java.util.List;

public class FlowDefinitionParam {

    private Long id;

    private String flowName;

    private String remark;

    private List<InputParameterParam> inputs;

    private List<OutputParameterParam> outputs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<InputParameterParam> getInputs() {
        return inputs;
    }

    public void setInputs(List<InputParameterParam> inputs) {
        this.inputs = inputs;
    }

    public List<OutputParameterParam> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<OutputParameterParam> outputs) {
        this.outputs = outputs;
    }
}
