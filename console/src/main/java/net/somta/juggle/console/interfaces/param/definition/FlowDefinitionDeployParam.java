package net.somta.juggle.console.interfaces.param.definition;

/**
 * @author husong
 */
public class FlowDefinitionDeployParam {
    private Long id;
    private String flowVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowVersion() {
        return flowVersion;
    }

    public void setFlowVersion(String flowVersion) {
        this.flowVersion = flowVersion;
    }
}
