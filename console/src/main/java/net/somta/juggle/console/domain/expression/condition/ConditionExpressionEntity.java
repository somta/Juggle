package net.somta.juggle.console.domain.expression.condition;

import net.somta.juggle.console.domain.expression.condition.parser.ExpressionParserFactory;
import net.somta.juggle.console.domain.expression.condition.parser.IExpressionParser;
import net.somta.juggle.core.model.node.ConditionNode.ConditionExpression;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author husong
 */
public class ConditionExpressionEntity {

    public static String generateExpression(List<List<ConditionExpression>> expressionList){
        if(CollectionUtils.isEmpty(expressionList)){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < expressionList.size(); i++) {
            List<ConditionExpression> andExpressionList = expressionList.get(i);
            String andExpression = handleAndExpression(andExpressionList);
            stringBuilder.append(andExpression);
            if (i < expressionList.size() - 1) {
                stringBuilder.append("||");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 处理且的判断表达式
     * @return
     */
    private static String handleAndExpression(List<ConditionExpression> andExpressionList){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < andExpressionList.size(); i++) {
            ConditionExpression conditionExpression = andExpressionList.get(i);
            IExpressionParser expressionParser = ExpressionParserFactory.getParserInstance(conditionExpression.getDataType());
            String expression = expressionParser.genExpression(conditionExpression);
            stringBuilder.append(expression);
            if (i < andExpressionList.size() - 1) {
                stringBuilder.append("&&");
            }
        }
        return stringBuilder.toString();
    }
}
