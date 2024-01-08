package net.somta.juggle.core.expression.condition.parser;

import net.somta.juggle.core.model.node.ConditionNode.ConditionExpression;

/**
 * @author husong
 */
public interface IExpressionParser {
    public String genExpression(ConditionExpression conditionExpression);
}
