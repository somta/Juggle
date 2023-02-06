package net.somta.juggle.core.executor;

import net.somta.juggle.core.enums.FlowElementTypeEnum;
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
        ElementExecutor elementExecutor = getElementExecutor(flowElement.getFlowElementType());
        return elementExecutor;
    }

    private static ElementExecutor getElementExecutor(FlowElementTypeEnum flowElementType) {
        switch (flowElementType) {
            case START: return new StartNodeExecutor();
            case END: return new EndNodeExecutor();
            case METHOD: return new MethodNodeExecutor();
            case CODITION: return new ConditionNodeExecutor();
            default: return null;
        }
    }

}
