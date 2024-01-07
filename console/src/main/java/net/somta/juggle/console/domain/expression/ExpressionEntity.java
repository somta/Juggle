package net.somta.juggle.console.domain.expression;

import net.somta.juggle.console.domain.expression.parser.ExpressionParserFactory;
import net.somta.juggle.console.domain.expression.parser.IExpressionParser;
import net.somta.juggle.console.domain.expression.vo.ExpressionVO;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author husong
 */
public class ExpressionEntity {

    public static String generateExpression(List<List<ExpressionVO>> expressionList){
        if(CollectionUtils.isEmpty(expressionList)){
            return null;
        }
        List<String> expressions = new ArrayList<>();
        for (List<ExpressionVO> andExpressionList : expressionList) {
            handleAndExpression(andExpressionList);
        }
        return null;
    }

    /**
     * 处理且的判断表达式
     * @return
     */
    private static String handleAndExpression(List<ExpressionVO> andExpressionList){
        StringBuilder stringBuilder = new StringBuilder();
        for (ExpressionVO expressionVo : andExpressionList) {
            IExpressionParser expressionParser = ExpressionParserFactory.getParserInstance(expressionVo.getDataType());
            String expression = expressionParser.genExpression(expressionVo);
            stringBuilder.append(expression);
            //todo 这里要拼接&& 逻辑待处理
        }
        return null;
    }
}
