package net.somta.juggle.console.interfaces.param;

/**
 * @author Gavin
 */
public class FlowStatusParam {

    private Long flowVersionId;

    private Integer flowStatus;

    public Long getFlowVersionId() {
        return flowVersionId;
    }

    public void setFlowVersionId(Long flowVersionId) {
        this.flowVersionId = flowVersionId;
    }

    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }
}
