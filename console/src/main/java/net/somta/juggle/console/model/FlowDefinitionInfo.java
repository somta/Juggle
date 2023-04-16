package net.somta.juggle.console.model;

import net.somta.core.base.BaseModel;

public class FlowDefinitionInfo extends BaseModel {

    private Long id;

    /**
     * 流程Key,全局唯一
     */
    private String flowKey;

    /**
     * 流程名称
     */
    private String flowName;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 流程描述
     */
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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
