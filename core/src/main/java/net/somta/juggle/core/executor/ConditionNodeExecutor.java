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
import net.somta.juggle.core.expression.ExpressionManager;
import net.somta.juggle.core.model.node.ConditionNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 判断节点执行器
 *
 * @author husong
 * @since 1.0.0
 */
public class ConditionNodeExecutor extends AbstractElementExecutor {
    private final static Logger logger = LoggerFactory.getLogger(ConditionNodeExecutor.class);

    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {
        logger.debug("判断节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        ExpressionManager expressionManager = new ExpressionManager(flowRuntimeContext.getVariableManager());
        ConditionNode conditionNode = (ConditionNode) flowRuntimeContext.getCurrentNode();
        List<ConditionNode.ConditionItem> conditions =  conditionNode.getConditions();
        String hitOutGoingKey = null;
        for (ConditionNode.ConditionItem conditionItem : conditions) {
            String expression = conditionItem.getExpression();
            boolean result = expressionManager.executeExpression(expression);
            if(result){
                hitOutGoingKey = conditionItem.getOutgoing();
                break;
            }
        }
        flowRuntimeContext.setCurrentNode(flowRuntimeContext.getFlowElementMap().get(hitOutGoingKey));
        logger.debug("判断节点执行器，执行中。。。");
    }

    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        logger.debug("判断节点执行器，执行后========================================");
    }

}
