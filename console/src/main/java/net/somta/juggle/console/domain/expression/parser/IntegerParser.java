package net.somta.juggle.console.domain.expression.parser;

import net.somta.juggle.console.domain.expression.enums.OperatorEnum;
import net.somta.juggle.console.domain.expression.vo.ExpressionVO;

/**
 * @author husong
 */
public class IntegerParser implements IExpressionParser {
    @Override
    public String genExpression(ExpressionVO expressionVo) {
        if(OperatorEnum.EQUAL.equals(expressionVo.getOperator())){
            return expressionVo.getEnvKey() + "==" + expressionVo.getValue();
        }else if(OperatorEnum.NOT_EQUAL.equals(expressionVo.getOperator())){
            return expressionVo.getEnvKey() + "!=" + expressionVo.getValue();
        }
        return null;
    }
}
