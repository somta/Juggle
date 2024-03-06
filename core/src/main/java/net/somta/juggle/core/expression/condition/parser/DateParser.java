package net.somta.juggle.core.expression.condition.parser;

import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.node.ConditionNode.ConditionExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author husong
 */
public class DateParser implements IExpressionParser {
    private final static Logger logger = LoggerFactory.getLogger(DateParser.class);

    @Override
    public String genExpression(ConditionExpression conditionExpression) {
        OperatorEnum operatorEnum = OperatorEnum.getByCode(conditionExpression.getOperator());
        String expression = null;
        switch (operatorEnum) {
            case EQUAL:
                expression = "date.eq("+conditionExpression.getEnvKey()+",'"+conditionExpression.getValue()+"')";
                break;
            case NOT_EQUAL:
                expression = "!date.eq("+conditionExpression.getEnvKey()+",'"+conditionExpression.getValue()+"')";
                break;
            case GREATER_THAN:
                expression = "date.gt("+conditionExpression.getEnvKey()+",'"+conditionExpression.getValue()+"')";
                break;
            case GREATER_THAN_OR_EQUAL:
                expression = "date.ge("+conditionExpression.getEnvKey()+",'"+conditionExpression.getValue()+"')";
                break;
            case LESS_THAN:
                expression = "date.lt("+conditionExpression.getEnvKey()+",'"+conditionExpression.getValue()+"')";
                break;
            case LESS_THAN_OR_EQUAL:
                expression = "date.le("+conditionExpression.getEnvKey()+",'"+conditionExpression.getValue()+"')";
                break;
            default:
                throw new IllegalArgumentException("日期类型不支持"+operatorEnum.getCode()+"操作符");
        }
        return expression;
    }
}
