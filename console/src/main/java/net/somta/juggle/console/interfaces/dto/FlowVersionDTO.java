package net.somta.juggle.console.interfaces.dto;

/**
 * @author husong
 */
public class FlowVersionDTO {

    private Long id;

    private String flowName;

    private String flowVersion;

    private String triggerUrl;

    private Integer flowVersionStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowVersion() {
        return flowVersion;
    }

    public void setFlowVersion(String flowVersion) {
        this.flowVersion = flowVersion;
    }

    public String getTriggerUrl() {
        return triggerUrl;
    }

    public void setTriggerUrl(String triggerUrl) {
        this.triggerUrl = triggerUrl;
    }

    public Integer getFlowVersionStatus() {
        return flowVersionStatus;
    }

    public void setFlowVersionStatus(Integer flowVersionStatus) {
        this.flowVersionStatus = flowVersionStatus;
    }
}
