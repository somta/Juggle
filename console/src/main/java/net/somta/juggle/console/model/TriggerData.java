package net.somta.juggle.console.model;

import java.util.Map;

/**
 * 触发流程的实体
 */
public class TriggerData {
    /**
     * 流程的触发Key
     */
    private String flowKey;
    /**
     * 触发流程需要的数据
     */
    private Map<String,Object> flowData;

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public Map<String, Object> getFlowData() {
        return flowData;
    }

    public void setFlowData(Map<String, Object> flowData) {
        this.flowData = flowData;
    }
}
