package net.somta.juggle.core.expression.condition.parser;

import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.node.ConditionNode.ConditionExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author husong
 */
public class DoubleParser implements IExpressionParser {
    private final static Logger logger = LoggerFactory.getLogger(DoubleParser.class);
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
                throw new IllegalArgumentException("小数类型不支持"+operatorEnum.getCode()+"操作符");
        }
        return expression;
    }
}
