package net.somta.juggle.core.executor;

import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.model.FlowElement;

import java.util.List;
import java.util.Map;

/**
 * 抽象的元素节点执行器
 *
 * @author husong
 * @date 2023/02/06
 */
public abstract class ElementExecutor implements IExecutor {

    @Override
    public void execute(RuntimeContext runtimeContext) {
        try {
            doPreExecute(runtimeContext);
            doExecute(runtimeContext);
        } catch (Exception ex) {
            System.out.println("执行流程出现异常了。。。。");
        } finally {
            doPostExecute(runtimeContext);
        }
    }

    @Override
    public IExecutor getExecutor(RuntimeContext runtimeContext) {
        Map<String, FlowElement> flowElementMap = runtimeContext.getFlowElementMap();
        FlowElement flowElement = getNextNode(runtimeContext.getCurrentNode(), flowElementMap);
        runtimeContext.setCurrentNode(flowElement);
        return ExecutorFactory.getElementExecutor(flowElement);
    }

    /**
     * 获取下一个节点，理论上下一个节点只会返回一个
     * @param currentNode
     * @param flowElementMap
     * @return
     */
    private FlowElement getNextNode(FlowElement currentNode, Map<String, FlowElement> flowElementMap) {
        /*List<String> outgoingKeyList = currentNode.getOutgoing();
        String nextElementKey = outgoingKeyList.get(0);
        FlowElement nextFlowElement = FlowModelUtil.getFlowElement(flowElementMap, nextElementKey);
        while (nextFlowElement.getType() == FlowElementType.SEQUENCE_FLOW) {
            nextFlowElement = getUniqueNextNode(nextFlowElement, flowElementMap);
        }
        return nextFlowElement;*/

        return null;
    }


    protected abstract void doPreExecute(RuntimeContext runtimeContext);

    protected abstract void doExecute(RuntimeContext runtimeContext);

    protected abstract void doPostExecute(RuntimeContext runtimeContext);
}
