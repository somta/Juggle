package net.somta.juggle.console.domain.expression.parser;

import net.somta.juggle.console.domain.expression.vo.ExpressionVO;

/**
 * @author husong
 */
public interface IExpressionParser {
    public String genExpression(ExpressionVO expressionVo);
}
