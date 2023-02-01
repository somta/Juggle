package net.somta.juggle.core.model.node;

import java.util.List;

/**
 * 结束事件节点
 *
 * @author husong
 * @date 2023/01/17
 */
public class EndEventNode extends EventNode {

    /**
     * 流程入口
     */
    private List<String> incomings;

    public List<String> getIncomings() {
        return incomings;
    }

    public void setIncomings(List<String> incomings) {
        this.incomings = incomings;
    }
}
