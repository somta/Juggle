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
            case EMPTY:
                expression = "list.empty("+conditionExpression.getEnvKey()+")";
                break;
            case NOT_EMPTY:
                expression = "!list.empty("+conditionExpression.getEnvKey()+")";
                break;
            default:
                throw new IllegalArgumentException("集合类型不支持"+operatorEnum.getCode()+"操作符");
        }
        return expression;
    }
}
