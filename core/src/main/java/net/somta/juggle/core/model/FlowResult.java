package net.somta.juggle.core.model;

import net.somta.juggle.core.enums.FlowStatusEnum;

import java.util.Map;

public class FlowResult {
    private String flowInstanceId;
    private FlowStatusEnum status;
    private Map<String,Object> data;

    public String getFlowInstanceId() {
        return flowInstanceId;
    }

    public FlowResult setFlowInstanceId(String flowInstanceId) {
        this.flowInstanceId = flowInstanceId;
        return this;
    }

    public FlowStatusEnum getStatus() {
        return status;
    }

    public FlowResult setStatus(FlowStatusEnum status) {
        this.status = status;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public FlowResult setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}
