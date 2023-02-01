package net.somta.juggle.core.model.node;

import net.somta.juggle.core.model.FlowElement;

import java.util.List;

/**
 * 流程节点类
 * @author husong
 * @date 2022/12/15
 **/
public class FlowNode extends FlowElement {
    /**
     * 流程入口
     */
    private List<String> incomings;
    /**
     * 流程出口
     */
    private List<String> outgoings;

    public List<String> getIncomings() {
        return incomings;
    }

    public void setIncomings(List<String> incomings) {
        this.incomings = incomings;
    }

    public List<String> getOutgoings() {
        return outgoings;
    }

    public void setOutgoings(List<String> outgoings) {
        this.outgoings = outgoings;
    }
}
