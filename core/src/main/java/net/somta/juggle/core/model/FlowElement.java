package net.somta.juggle.core.model;

import net.somta.juggle.core.enums.FlowElementTypeEnum;

/**
 * 流程元素类
 * @author husong
 * @date 2022/12/15
 **/
public class FlowElement {
    /**
     * 流程内元素唯一标识
     */
    private String key;
    /**
     * 流程元素类型
     */
    private FlowElementTypeEnum flowElementType;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FlowElementTypeEnum getFlowElementType() {
        return flowElementType;
    }

    public void setFlowElementType(FlowElementTypeEnum flowElementType) {
        this.flowElementType = flowElementType;
    }
}
