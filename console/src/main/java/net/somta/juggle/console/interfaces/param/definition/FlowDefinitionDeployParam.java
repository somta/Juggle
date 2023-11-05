package net.somta.juggle.console.interfaces.param.definition;

/**
 * @author husong
 */
public class FlowDefinitionDeployParam {
    private Long flowDefinitionId;
    private String flowDeployVersion;

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
}
