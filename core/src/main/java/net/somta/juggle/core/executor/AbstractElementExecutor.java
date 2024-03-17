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
            preExecute(flowRuntimeContext);
            doExecute(flowRuntimeContext);
        } finally {
            doPostExecute(flowRuntimeContext);
        }
    }

    @Override
    public IExecutor getNextNodeExecutor(FlowRuntimeContext flowRuntimeContext) {
        FlowElement nextFlowElement = flowRuntimeContext.getNextNode();
        return ExecutorFactory.getElementExecutor(nextFlowElement);
    }

    /**
     *
     * @param flowRuntimeContext
     */
    private void preExecute(FlowRuntimeContext flowRuntimeContext){
        if(flowRuntimeContext.getNextNode() != null){
            flowRuntimeContext.setCurrentNode(flowRuntimeContext.getNextNode());
        }
        doPreExecute(flowRuntimeContext);
    }

    /**
     * 当前节点执行完成后，填充上下文的下一个节点实例
     * @param flowRuntimeContext
     * @param nextNodeKey
     */
    protected void fillNextNode(FlowRuntimeContext flowRuntimeContext,String nextNodeKey){
        Map<String, FlowElement> flowElementMap = flowRuntimeContext.getFlowElementMap();
        FlowElement nextNode = flowElementMap.get(nextNodeKey);
        flowRuntimeContext.setNextNode(nextNode);
    }


    protected abstract void doPreExecute(FlowRuntimeContext flowRuntimeContext);

    protected abstract void doExecute(FlowRuntimeContext flowRuntimeContext);

    protected abstract void doPostExecute(FlowRuntimeContext flowRuntimeContext);
}
