package net.somta.juggle.console.domain.flow.version.vo;

/**
 * @author husong
 */
public class FlowVersionQueryVO {

    private Long flowId;

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

    public Integer getFlowVersionStatus() {
        return flowVersionStatus;
    }

    public void setFlowVersionStatus(Integer flowVersionStatus) {
        this.flowVersionStatus = flowVersionStatus;
    }
}
