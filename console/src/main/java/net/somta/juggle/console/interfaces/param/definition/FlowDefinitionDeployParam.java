package net.somta.juggle.console.interfaces.param.definition;

/**
 * @author husong
 */
public class FlowDefinitionDeployParam {
    private Long FlowDefinitionId;
    private String flowDeployVersion;

    public Long getFlowDefinitionId() {
        return FlowDefinitionId;
    }

    public void setFlowDefinitionId(Long flowDefinitionId) {
        FlowDefinitionId = flowDefinitionId;
    }

    public String getFlowDeployVersion() {
        return flowDeployVersion;
    }

    public void setFlowDeployVersion(String flowDeployVersion) {
        this.flowDeployVersion = flowDeployVersion;
    }
}
