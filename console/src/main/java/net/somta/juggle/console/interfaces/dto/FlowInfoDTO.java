package net.somta.juggle.console.interfaces.dto;

/**
 * @author husong
 */
public class FlowInfoDTO {
    private Long id;

    private String flowKey;

    private String flowName;

    private String flowType;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FlowInfoDTO{" +
                "id=" + id +
                ", flowKey='" + flowKey + '\'' +
                ", flowName='" + flowName + '\'' +
                ", flowType='" + flowType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
