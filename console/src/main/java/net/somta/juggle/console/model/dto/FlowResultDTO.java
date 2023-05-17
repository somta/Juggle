package net.somta.juggle.console.model.dto;

import java.util.Map;

public class FlowResultDTO {
   private String flowInstanceId;
   private Map<String,Object> result;

    public String getFlowInstanceId() {
        return flowInstanceId;
    }

    public void setFlowInstanceId(String flowInstanceId) {
        this.flowInstanceId = flowInstanceId;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
