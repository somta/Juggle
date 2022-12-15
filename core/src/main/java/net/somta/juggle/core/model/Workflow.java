package net.somta.juggle.core.model;

import java.util.List;

/**
 * 流程类
 * @author husong
 * @date 2022/12/14
 **/
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
    private List<InputParameter> inputs;
    /**
     * 出参列表
     */
    private List<OutputParameter> outputs;
    /**
     * 变量列表
     */
    // TODO 变量类型分为独享和，共享（一个业务key的多个流程共享）
    private List variables;


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

    public List getVariables() {
        return variables;
    }

    public void setVariables(List variables) {
        this.variables = variables;
    }
}
