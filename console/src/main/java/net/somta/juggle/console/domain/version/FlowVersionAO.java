package net.somta.juggle.console.domain.version;

import net.somta.juggle.console.domain.version.enums.FlowVersionStatusEnum;

public class FlowVersionAO {
    private Long id;

    private Long flowId;

    private String flowKey;

    private String flowName;
    private String flowType;

    private String flowVersion;
    /**
     * 流程状态   0:禁用  1:启用
     */
    private FlowVersionStatusEnum flowVersionStatus;

    private String flowContent;

    private String inputs;

    private String outputs;

    private String variables;

    public void setNegateStatus(FlowVersionStatusEnum flowVersionStatus){
        if(FlowVersionStatusEnum.DISABLED == flowVersionStatus){
            this.flowVersionStatus = FlowVersionStatusEnum.ENABLE;
        } else {
            this.flowVersionStatus = FlowVersionStatusEnum.DISABLED;
        }
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

    public FlowVersionStatusEnum getFlowVersionStatus() {
        return flowVersionStatus;
    }

    public void setFlowVersionStatus(FlowVersionStatusEnum flowVersionStatus) {
        this.flowVersionStatus = flowVersionStatus;
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
