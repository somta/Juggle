package net.somta.juggle.core.executor;

import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.model.FlowElement;

/**
 * 执行器工厂
 *
 * @author husong
 */
public class ExecutorFactory {

    /**
     * 根据节点类型返回对应节点的执行器
     * @param flowElement
     * @return
     */
    public static AbstractElementExecutor getElementExecutor(FlowElement flowElement) {
        if(flowElement == null){
            return null;
        }
        AbstractElementExecutor abstractElementExecutor = getElementExecutor(flowElement.getElementType());
        return abstractElementExecutor;
    }

    /**
     * 获取元素的执行器
     * @param flowElementType
     * @return
     */
    private static AbstractElementExecutor getElementExecutor(ElementTypeEnum flowElementType) {
        switch (flowElementType) {
            case START: return new StartNodeExecutor();
            case END: return new EndNodeExecutor();
            case METHOD: return new MethodNodeExecutor();
            case CONDITION: return new ConditionNodeExecutor();
            default: return null;
        }
    }

}
