package net.somta.juggle.core.model;

import java.util.List;

/**
 * TODO 类职责描述
 *
 * @author husong
 * @date 2023/01/04
 */
public class FlowDefinition {

    /**
     * 主键
     */
    private Long id;

    /**
     * 流程Key,全局唯一
     */
    private String flowKey;
    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 流程入参
     */
    private List<InputParameter> inputParameters;

    /**
     * 流程出参
     */
    private List<OutputParameter> outputParameters;

    /**
     * 流程Json
     */
    private String content;

    /**
     * 流程描述
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public List<InputParameter> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(List<InputParameter> inputParameters) {
        this.inputParameters = inputParameters;
    }

    public List<OutputParameter> getOutputParameters() {
        return outputParameters;
    }

    public void setOutputParameters(List<OutputParameter> outputParameters) {
        this.outputParameters = outputParameters;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
