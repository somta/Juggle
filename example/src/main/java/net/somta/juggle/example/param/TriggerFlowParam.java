package net.somta.juggle.example.param;

import net.somta.juggle.common.param.TriggerDataParam;

/**
 * @author husong
 */
public class TriggerFlowParam {

    private String flowKey;

    private String flowVersion;

    private TriggerDataParam triggerData;

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getFlowVersion() {
        return flowVersion;
    }

    public void setFlowVersion(String flowVersion) {
        this.flowVersion = flowVersion;
    }

    public TriggerDataParam getTriggerData() {
        return triggerData;
    }

    public void setTriggerData(TriggerDataParam triggerData) {
        this.triggerData = triggerData;
    }
}
