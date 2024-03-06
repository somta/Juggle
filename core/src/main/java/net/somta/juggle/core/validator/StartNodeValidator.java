package net.somta.juggle.core.validator;

import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.node.FlowNode;
import net.somta.juggle.core.model.node.StartNode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author husong
 */
public class StartNodeValidator extends AbstractElementValidator{
    @Override
    protected void doValidateIncoming(FlowElement flowElement) {

    }

    @Override
    protected void doValidateOutgoing(FlowElement flowElement) {
        StartNode startNode = (StartNode)flowElement;
        List<String> outgoings = startNode.getOutgoings();
        if(CollectionUtils.isEmpty(outgoings) || (CollectionUtils.isNotEmpty(outgoings) && outgoings.size() > 1)){
            // todo 这里要抛出一个异常
        }
    }
}
