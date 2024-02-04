package net.somta.juggle.core.expression.condition.parser;

import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.node.ConditionNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author husong
 */
public class ObjectParser implements IExpressionParser{
    private final static Logger logger = LoggerFactory.getLogger(ObjectParser.class);
    @Override
    public String genExpression(ConditionNode.ConditionExpression conditionExpression) {
        OperatorEnum operatorEnum = OperatorEnum.getByCode(conditionExpression.getOperator());
        String expression = null;
        switch (operatorEnum) {
            case EMPTY:
                expression = conditionExpression.getEnvKey() + "==null";
                break;
            case NOT_EMPTY:
                expression = conditionExpression.getEnvKey() + "!=null";
                break;
            default:
                throw new IllegalArgumentException("对象类型不支持"+operatorEnum.getCode()+"操作符");
        }
        return expression;
    }
}
