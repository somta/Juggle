package net.somta.juggle.core.validator;

import net.somta.juggle.core.model.FlowElement;
/**
 * 抽象的元素节点校验器
 *
 * @author husong
 */
public abstract class AbstractElementValidator implements IValidator {

    @Override
    public Boolean validateFlow(FlowElement flowElement) {
        doValidateIncoming(flowElement);
        doValidateOutgoing(flowElement);
        return true;
    }

    protected abstract void doValidateIncoming(FlowElement flowElement);

    protected abstract void doValidateOutgoing(FlowElement flowElement);

}
