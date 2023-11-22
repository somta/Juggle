package net.somta.juggle.console.interfaces.param.definition;

/**
 * @author husong
 */
public class FlowDefinitionDeployParam {
    private Long flowDefinitionId;
    private String flowDeployVersion;
    private String flowVersionRemark;

    public Long getFlowDefinitionId() {
        return flowDefinitionId;
    }

    public void setFlowDefinitionId(Long flowDefinitionId) {
        this.flowDefinitionId = flowDefinitionId;
    }

    public String getFlowDeployVersion() {
        return flowDeployVersion;
    }

    public void setFlowDeployVersion(String flowDeployVersion) {
        this.flowDeployVersion = flowDeployVersion;
    }

    public String getFlowVersionRemark() {
        return flowVersionRemark;
    }

    public void setFlowVersionRemark(String flowVersionRemark) {
        this.flowVersionRemark = flowVersionRemark;
    }
}
