/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.core.expression;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.expression.condition.parser.ExpressionParserFactory;
import net.somta.juggle.core.expression.condition.parser.IExpressionParser;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.variable.AbstractVariableManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author husong
 * @since 1.0.0
 */
public class ExpressionManager {
    private final static Logger logger = LoggerFactory.getLogger(ExpressionManager.class);

    private AviatorEvaluatorInstance aviatorEvaluatorInstance = AviatorEvaluator.getInstance();
    private final AbstractVariableManager variableManager;

    public ExpressionManager(AbstractVariableManager variableManager) {
        this.variableManager = variableManager;
    }

    /**
     * 执行表达式
     * @param expression 表达式
     * @return
     */
    public boolean executeExpression(String expression) {
        if(StringUtils.isEmpty(expression)){
            return true;
        }
        Expression compiledExp = aviatorEvaluatorInstance.compile(expression, true);
        List<String> variableKeys = compiledExp.getVariableNames();
        Map<String, Object> env = new HashMap<>(8);
        for (String key : variableKeys){
            try {
                env.put(key,variableManager.getVariableValue(key));
            } catch (FlowException e) {
                e.printStackTrace();
            }
        }
        // 执行表达式
        boolean result = (Boolean) compiledExp.execute(env);
        logger.debug("expression:{},env:{},result",expression,env,result);
        return result;
    }

    public static String generateExpression(List<List<ConditionNode.ConditionExpression>> expressionList){
        if(CollectionUtils.isEmpty(expressionList)){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < expressionList.size(); i++) {
            List<ConditionNode.ConditionExpression> andExpressionList = expressionList.get(i);
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
    private static String handleAndExpression(List<ConditionNode.ConditionExpression> andExpressionList){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < andExpressionList.size(); i++) {
            ConditionNode.ConditionExpression conditionExpression = andExpressionList.get(i);
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
