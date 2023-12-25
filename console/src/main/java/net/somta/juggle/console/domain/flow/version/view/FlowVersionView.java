package net.somta.juggle.console.domain.flow.version.view;

/**
 * @author husong
 */
public class FlowVersionView {
    private Long id;

    private String flowName;

    private String flowKey;

    private String flowVersion;

    private Integer flowVersionStatus;

    private String flowVersionRemark;

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

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
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
}
