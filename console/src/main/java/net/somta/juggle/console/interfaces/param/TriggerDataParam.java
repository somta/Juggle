package net.somta.juggle.console.interfaces.param;

import java.util.Map;

/**
 * 触发流程的实体
 * @author husong
 */
public class TriggerDataParam {
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
