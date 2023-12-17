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
 * @date 2023/02/06
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
