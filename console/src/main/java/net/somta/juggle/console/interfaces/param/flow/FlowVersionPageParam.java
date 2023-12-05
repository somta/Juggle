package net.somta.juggle.console.interfaces.param.flow;

import net.somta.core.base.page.PageParam;

/**
 * @author husong
 */
public class FlowVersionPageParam extends PageParam {

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
