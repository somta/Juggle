package net.somta.juggle.console.interfaces.param;

import net.somta.core.base.page.PageParam;

/**
 * @author husong
 */
public class FlowInfoPageParam extends PageParam {

    private String flowName;

    private String flowType;

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }
}
