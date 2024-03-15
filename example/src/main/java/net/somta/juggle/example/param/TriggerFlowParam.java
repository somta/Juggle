package net.somta.juggle.example.param;


import net.somta.juggle.starter.model.FlowTriggerDataParam;

/**
 * @author husong
 */
public class TriggerFlowParam {

    private String flowKey;

    private String flowVersion;

    private FlowTriggerDataParam triggerData;

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

    public FlowTriggerDataParam getTriggerData() {
        return triggerData;
    }

    public void setTriggerData(FlowTriggerDataParam triggerData) {
        this.triggerData = triggerData;
    }
}
