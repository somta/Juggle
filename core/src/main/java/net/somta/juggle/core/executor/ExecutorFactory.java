package net.somta.juggle.core.executor;

import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.model.FlowElement;

/**
 * 执行器工厂
 *
 * @author husong
 * @date 2023/02/06
 */
public class ExecutorFactory {

    /**
     * 根据节点类型返回对应节点的执行器
     * @param flowElement
     * @return
     */
    public static ElementExecutor getElementExecutor(FlowElement flowElement) {
        ElementExecutor elementExecutor = getElementExecutor(flowElement.getElementType());
        return elementExecutor;
    }

    /**
     * 获取元素的执行器
     * @param flowElementType
     * @return
     */
    private static ElementExecutor getElementExecutor(ElementTypeEnum flowElementType) {
        switch (flowElementType) {
            case START: return new StartNodeExecutor();
            case END: return new EndNodeExecutor();
            case METHOD: return new MethodNodeExecutor();
            case CONDITION: return new ConditionNodeExecutor();
            default: return null;
        }
    }

}
