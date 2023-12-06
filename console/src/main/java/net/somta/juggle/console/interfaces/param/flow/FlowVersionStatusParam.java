package net.somta.juggle.console.interfaces.param.flow;

/**
 * @author Gavin
 */
public class FlowVersionStatusParam {

    private Long flowVersionId;

    private Integer flowVersionStatus;

    public Long getFlowVersionId() {
        return flowVersionId;
    }

    public void setFlowVersionId(Long flowVersionId) {
        this.flowVersionId = flowVersionId;
    }

    public Integer getFlowVersionStatus() {
        return flowVersionStatus;
    }

    public void setFlowVersionStatus(Integer flowVersionStatus) {
        this.flowVersionStatus = flowVersionStatus;
    }
}
