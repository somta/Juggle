package net.somta.juggle.core.validator;

import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.node.FlowNode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author husong
 */
public class StartNodeValidator extends AbstractElementValidator{
    @Override
    protected void doValidateIncoming(FlowNode flowNode) {

    }

    @Override
    protected void doValidateOutgoing(FlowNode flowNode) {
        List<String> outgoings = flowNode.getOutgoings();
        if(CollectionUtils.isEmpty(outgoings) || (CollectionUtils.isNotEmpty(outgoings) && outgoings.size() > 1)){
            // todo 这里要抛出一个异常
        }
    }
}
