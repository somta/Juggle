package net.somta.juggle.core.validator;

import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.node.FlowNode;

/**
 * 抽象的元素节点校验器
 *
 * @author husong
 */
public abstract class AbstractElementValidator implements IValidator {

    @Override
    public Boolean validateFlow(FlowNode flowNode) {
        doValidateIncoming(flowNode);
        doValidateOutgoing(flowNode);
        return true;
    }

    protected abstract void doValidateIncoming(FlowNode flowNode);

    protected abstract void doValidateOutgoing(FlowNode flowNode);

}
