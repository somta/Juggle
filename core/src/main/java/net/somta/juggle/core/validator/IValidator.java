package net.somta.juggle.core.validator;

import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.node.FlowNode;

/**
 * 校验器接口
 *
 * @author husong
 * @date 2023/02/09
 */
public interface IValidator {

    Boolean validateFlow(FlowElement flowElement);


}
