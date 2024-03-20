package net.somta.juggle.console.domain.flow.version.vo;

/**
 * @author husong
 */
public class FlowVersionQueryVO {

    private Long flowId;

    private String flowKey;

    private String flowVersion;

    /**
     * 流程状态   0:禁用  1:启用
     */
    private Integer flowVersionStatus;

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
}
