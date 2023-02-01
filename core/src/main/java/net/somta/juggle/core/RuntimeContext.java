package net.somta.juggle.core;

import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.model.FlowElement;

import java.util.Map;

/**
 * 流程执行的上下文
 * @author husong
 * @date 2022/12/14
 **/
public class RuntimeContext {


    /**
     * 流程Key,全局唯一
     */
    private String flowKey;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 流程元素Map
     */
    private Map<String, FlowElement> flowMap;

    // todo 变量管理器

    /**
     * 流程状态
     */
    private FlowStatusEnum flowStatus;

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

    public Map<String, FlowElement> getFlowMap() {
        return flowMap;
    }

    public void setFlowMap(Map<String, FlowElement> flowMap) {
        this.flowMap = flowMap;
    }

    public FlowStatusEnum getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(FlowStatusEnum flowStatus) {
        this.flowStatus = flowStatus;
    }
}
