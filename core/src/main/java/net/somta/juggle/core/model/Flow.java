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
    private List<InputParameter> inputParams;
    /**
     * 出参列表
     */
    private List<OutputParameter> outputParams;
    /**
     * 变量列表
     */
    private List<Variable> variables;

    /**
     * 租户ID
     */
    private String tenantId;


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

    public List<InputParameter> getInputParams() {
        return inputParams;
    }

    public void setInputParams(List<InputParameter> inputParams) {
        this.inputParams = inputParams;
    }

    public List<OutputParameter> getOutputParams() {
        return outputParams;
    }

    public void setOutputParams(List<OutputParameter> outputParams) {
        this.outputParams = outputParams;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
