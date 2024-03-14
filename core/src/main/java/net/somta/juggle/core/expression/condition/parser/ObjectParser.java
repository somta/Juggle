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

import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.node.ConditionNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author husong
 * @since 1.0.0
 */
public class ObjectParser implements IExpressionParser{
    private final static Logger logger = LoggerFactory.getLogger(ObjectParser.class);
    @Override
    public String genExpression(ConditionNode.ConditionExpression conditionExpression) {
        OperatorEnum operatorEnum = OperatorEnum.getByCode(conditionExpression.getOperator());
        String expression = null;
        switch (operatorEnum) {
            case EMPTY:
                expression = "object.empty("+conditionExpression.getEnvKey()+")";
                break;
            case NOT_EMPTY:
                expression = "!object.empty("+conditionExpression.getEnvKey()+")";
                break;
            default:
                throw new IllegalArgumentException("对象类型不支持"+operatorEnum.getCode()+"操作符");
        }
        return expression;
    }
}
