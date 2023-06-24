package net.somta.juggle.console.model.param;

import net.somta.core.base.page.PageParam;

public class FlowDefinitionPageParam extends PageParam {

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
