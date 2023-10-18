package net.somta.juggle.console.domain.version.vo;

/**
 * @author husong
 */
public class FlowVersionVO {
    private Long id;

    private Long flowId;

    private String flowVersion;
    /**
     * 流程状态   0:禁用  1:启用
     */
    private Integer flowStatus;

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

    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }
}
