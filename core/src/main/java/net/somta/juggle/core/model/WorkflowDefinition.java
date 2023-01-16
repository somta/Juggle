package net.somta.juggle.core.model;

/**
 * TODO 类职责描述
 *
 * @author husong
 * @date 2023/01/04
 */
public class WorkflowDefinition {

    /**
     * 流程Key,全局唯一
     */
    private String flowKey;
    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 流程Json
     */
    private String content;

    /**
     * 流程备注
     */
    private String remark;


    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
