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
package net.somta.juggle.core.expression.condition.parser;

import net.somta.juggle.core.enums.AssignTypeEnum;
import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.node.ConditionNode.ConditionExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author husong
 * @since 1.0.0
 */
public class TimeParser implements IExpressionParser {
    private final static Logger logger = LoggerFactory.getLogger(TimeParser.class);

    @Override
    public String genExpression(ConditionExpression conditionExpression) {
        OperatorEnum operatorEnum = OperatorEnum.getByCode(conditionExpression.getOperator());
        String expression = null;
        switch (operatorEnum) {
            case EQUAL:
                expression = "time.eq("+conditionExpression.getEnvKey()+","+formatValue(conditionExpression.getValue(),conditionExpression.getAssignType())+")";
                break;
            case NOT_EQUAL:
                expression = "!time.eq("+conditionExpression.getEnvKey()+","+formatValue(conditionExpression.getValue(),conditionExpression.getAssignType())+")";
                break;
            case GREATER_THAN:
                expression = "time.gt("+conditionExpression.getEnvKey()+","+formatValue(conditionExpression.getValue(),conditionExpression.getAssignType())+")";
                break;
            case GREATER_THAN_OR_EQUAL:
                expression = "time.ge("+conditionExpression.getEnvKey()+","+formatValue(conditionExpression.getValue(),conditionExpression.getAssignType())+")";
                break;
            case LESS_THAN:
                expression = "time.lt("+conditionExpression.getEnvKey()+","+formatValue(conditionExpression.getValue(),conditionExpression.getAssignType())+")";
                break;
            case LESS_THAN_OR_EQUAL:
                expression = "time.le("+conditionExpression.getEnvKey()+","+formatValue(conditionExpression.getValue(),conditionExpression.getAssignType())+")";
                break;
            default:
                throw new IllegalArgumentException("时间类型不支持"+operatorEnum.getCode()+"操作符");
        }
        return expression;
    }

    private String formatValue(Object value, AssignTypeEnum assignType){
        if(AssignTypeEnum.CONSTANT.equals(assignType)){
            return "'" + value + "'";
        }
        return value.toString();
    }
}
