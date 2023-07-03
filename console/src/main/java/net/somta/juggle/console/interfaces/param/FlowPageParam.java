package net.somta.juggle.console.interfaces.param;

import net.somta.core.base.page.PageParam;

public class FlowPageParam extends PageParam {

    private String flowName;

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }
}
