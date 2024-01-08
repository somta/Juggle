package net.somta.juggle.core.expression.condition.parser;

import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.node.ConditionNode.ConditionExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author husong
 */
public class ListParser implements IExpressionParser {
    private final static Logger logger = LoggerFactory.getLogger(ListParser.class);
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
            case EMPTY:
                System.out.println("todo");
                break;
            case CONTAINS:
                System.out.println("todo");
                break;
            case NOT_CONTAINS:
                System.out.println("todo");
                break;
            case GREATER_THAN:
                System.out.println("todo");
                break;
            case NOT_LESS_THAN:
                System.out.println("todo");
                break;
            case LESS_THAN:
                System.out.println("todo");
                break;
            case NOT_GREATER_THAN:
                System.out.println("todo");
                break;
            default:
                logger.error("集合类型不支持该操作符");
                break;
        }
        return expression;
    }
}
