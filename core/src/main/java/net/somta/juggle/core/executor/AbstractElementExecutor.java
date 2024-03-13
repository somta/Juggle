/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.core.executor;

import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.model.node.EndNode;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.model.node.StartNode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 抽象的元素节点执行器
 *
 * @author husong
 * @since 1.0.0
 */
public abstract class AbstractElementExecutor implements IExecutor {

    @Override
    public void execute(FlowRuntimeContext flowRuntimeContext) {
        try {
            doPreExecute(flowRuntimeContext);
            doExecute(flowRuntimeContext);
        } finally {
            doPostExecute(flowRuntimeContext);
        }
    }

    @Override
    public IExecutor getExecutor(FlowRuntimeContext flowRuntimeContext) {
        Map<String, FlowElement> flowElementMap = flowRuntimeContext.getFlowElementMap();
        FlowElement flowElement = getNextNode(flowRuntimeContext.getCurrentNode(), flowElementMap);
        flowRuntimeContext.setCurrentNode(flowElement);
        return ExecutorFactory.getElementExecutor(flowElement);
    }

    /**
     * 获取下一个节点，理论上下一个节点只会返回一个
     * @param currentNode
     * @param flowElementMap
     * @return
     */
    private FlowElement getNextNode(FlowElement currentNode, Map<String, FlowElement> flowElementMap) {
        List<String> outgoingKeyList = null;
        if(currentNode instanceof StartNode){
            StartNode node = (StartNode)currentNode;
            outgoingKeyList = node.getOutgoings();
        }else if(currentNode instanceof EndNode){
            return null;
        }else if(currentNode instanceof MethodNode){
            MethodNode node = (MethodNode)currentNode;
            outgoingKeyList = node.getOutgoings();
        }else if(currentNode instanceof ConditionNode){
            ConditionNode node = (ConditionNode)currentNode;
            outgoingKeyList = node.getOutgoings();
        }

        FlowElement nextElement = null;
        if(!CollectionUtils.isEmpty(outgoingKeyList)){
            //todo 这里有没有更优的解法
            String nextElementKey = outgoingKeyList.get(0);
            nextElement = flowElementMap.get(nextElementKey);
        }
        return nextElement;

        /*List<String> outgoingKeyList = currentNode.getOutgoing();
        String nextElementKey = outgoingKeyList.get(0);
        FlowElement nextFlowElement = FlowModelUtil.getFlowElement(flowElementMap, nextElementKey);
        while (nextFlowElement.getType() == FlowElementType.SEQUENCE_FLOW) {
            nextFlowElement = getUniqueNextNode(nextFlowElement, flowElementMap);
        }
        return nextFlowElement;*/
    }


    protected abstract void doPreExecute(FlowRuntimeContext flowRuntimeContext);

    protected abstract void doExecute(FlowRuntimeContext flowRuntimeContext);

    protected abstract void doPostExecute(FlowRuntimeContext flowRuntimeContext);
}
