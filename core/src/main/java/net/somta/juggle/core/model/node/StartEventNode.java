package net.somta.juggle.core.model.node;

import java.util.List;

/**
 * 开始事件节点
 *
 * @author husong
 * @date 2023/01/17
 */
public class StartEventNode extends EventNode {
    /**
     * 流程出口
     */
    private List<String> outgoings;

    public List<String> getOutgoings() {
        return outgoings;
    }

    public void setOutgoings(List<String> outgoings) {
        this.outgoings = outgoings;
    }
}
