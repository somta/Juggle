package net.somta.juggle.core.expression.condition.parser;

import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.node.ConditionNode.ConditionExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author husong
 */
public class IntegerParser implements IExpressionParser {
    private final static Logger logger = LoggerFactory.getLogger(IntegerParser.class);
    @Override
    public String genExpression(ConditionExpression conditionExpression) {
        OperatorEnum operatorEnum = OperatorEnum.getByCode(conditionExpression.getOperator());
        String expression = null;
        switch (operatorEnum) {
            case EQUAL:
                expression = conditionExpression.getEnvKey() + "==" + conditionExpression.getValue();
                break;
            case NOT_EQUAL:
                expression = conditionExpression.getEnvKey() + "!=" + conditionExpression.getValue();
                break;
            case GREATER_THAN:
                expression = conditionExpression.getEnvKey() + ">" + conditionExpression.getValue();
                break;
            case NOT_LESS_THAN:
                expression = conditionExpression.getEnvKey() + ">=" + conditionExpression.getValue();
                break;
            case LESS_THAN:
                expression = conditionExpression.getEnvKey() + "<" + conditionExpression.getValue();
                break;
            case NOT_GREATER_THAN:
                expression = conditionExpression.getEnvKey() + "<=" + conditionExpression.getValue();
                break;
            default:
                logger.error("整数类型不支持该操作符");
                break;
        }
        return expression;
    }
}
