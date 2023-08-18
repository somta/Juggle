package net.somta.juggle.console.interfaces.param;

import java.util.List;

/**
 * @author Gavin
 */
public class FlowStatusParam {

    private Long flowId;

    private Integer flowStatus;

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }
}
