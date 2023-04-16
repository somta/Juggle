package net.somta.juggle.core.model;

import java.util.List;

/**
 * 流程类
 * @author husong
 * @date 2022/12/14
 **/
public class Flow {

    /**
     * 流程Key
     */
    private String flowKey;

    /**
     * 流程名称
     */
    private String flowName;

    /**
     * 流程内容
     */
    private String flowContent;
    /**
     * 入参列表
     */
    private List<InputParameter> inputs;
    /**
     * 出参列表
     */
    private List<OutputParameter> outputs;
    /**
     * 变量列表
     */
    private List<Variable> variables;

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

    public String getFlowContent() {
        return flowContent;
    }

    public void setFlowContent(String flowContent) {
        this.flowContent = flowContent;
    }

    public List<InputParameter> getInputs() {
        return inputs;
    }

    public void setInputs(List<InputParameter> inputs) {
        this.inputs = inputs;
    }

    public List<OutputParameter> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<OutputParameter> outputs) {
        this.outputs = outputs;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
}
