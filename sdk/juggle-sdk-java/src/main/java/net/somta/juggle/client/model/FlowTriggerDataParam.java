package net.somta.juggle.client.model;

import java.util.Map;

/**
 * 触发流程的实体
 * @author husong
 */
public class FlowTriggerDataParam {
    /**
     * 触发流程需要的数据
     */
    private Map<String,Object> flowData;

    public Map<String, Object> getFlowData() {
        return flowData;
    }

    public void setFlowData(Map<String, Object> flowData) {
        this.flowData = flowData;
    }
}
