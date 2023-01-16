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
     *
     */
    private Map<String, FlowElement> flowMap;

    // todo 变量管理器

    /**
     * 流程状态
     */
    private FlowStatusEnum flowStatus;


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
