package net.somta.juggle.console.infrastructure.po.flow;

import net.somta.core.base.BaseModel;

/**
 * @author Gavin
 */
public class FlowVersionPO extends BaseModel {
    private Long id;

    private Long flowId;

    private String flowVersion;
    /**
     * 流程状态   0:禁用  1:启用
     */
    private Integer flowVersionStatus;
    /**
     * 流程版本描述
     */
    private String flowVersionRemark;

    private String flowContent;

    private String inputs;

    private String outputs;

    private String variables;



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

    public String getFlowVersion() {
        return flowVersion;
    }

    public void setFlowVersion(String flowVersion) {
        this.flowVersion = flowVersion;
    }

    public Integer getFlowVersionStatus() {
        return flowVersionStatus;
    }

    public void setFlowVersionStatus(Integer flowVersionStatus) {
        this.flowVersionStatus = flowVersionStatus;
    }

    public String getFlowVersionRemark() {
        return flowVersionRemark;
    }

    public void setFlowVersionRemark(String flowVersionRemark) {
        this.flowVersionRemark = flowVersionRemark;
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
