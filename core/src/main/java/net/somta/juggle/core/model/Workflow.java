package net.somta.juggle.core.model;

import java.util.List;

public class Workflow {
    private String id;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 流程描述
     */
    private String description;
    /**
     * 入参列表
     */
    private List inputs;
    /**
     * 出参列表
     */
    private List outputs;
    /**
     * 变量列表
     */
    private List variableList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List getInputs() {
        return inputs;
    }

    public void setInputs(List inputs) {
        this.inputs = inputs;
    }

    public List getOutputs() {
        return outputs;
    }

    public void setOutputs(List outputs) {
        this.outputs = outputs;
    }

    public List getVariableList() {
        return variableList;
    }

    public void setVariableList(List variableList) {
        this.variableList = variableList;
    }
}
