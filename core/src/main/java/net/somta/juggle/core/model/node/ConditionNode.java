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
package net.somta.juggle.core.model.node;

import net.somta.juggle.core.enums.AssignTypeEnum;
import net.somta.juggle.core.model.DataType;

import java.util.List;

/**
 * 判断节点
 *
 * @author husong
 * @since 1.0.0
 */
public class ConditionNode extends FlowNode{

    /**
     * 条件表达式Map <下节点ID，表达式>
     */
    private List<ConditionItem> conditions;

    public List<ConditionItem> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionItem> conditions) {
        this.conditions = conditions;
    }

    public static class ConditionItem{
        private String conditionName;
        private ConditionType conditionType;
        private String outgoing;
        private String expression;
        private List<List<ConditionExpression>> conditionExpressions;

        public String getConditionName() {
            return conditionName;
        }

        public void setConditionName(String conditionName) {
            this.conditionName = conditionName;
        }

        public ConditionType getConditionType() {
            return conditionType;
        }

        public void setConditionType(ConditionType conditionType) {
            this.conditionType = conditionType;
        }

        public String getOutgoing() {
            return outgoing;
        }

        public void setOutgoing(String outgoing) {
            this.outgoing = outgoing;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public List<List<ConditionExpression>> getConditionExpressions() {
            return conditionExpressions;
        }

        public void setConditionExpressions(List<List<ConditionExpression>> conditionExpressions) {
            this.conditionExpressions = conditionExpressions;
        }
    }

    public static class ConditionExpression{
        /**
         * 原变量key
         */
        private String envKey;

        /**
         * 原变量的类型
         */
        private DataType dataType;

        /**
         * 操作符 等于 不等于
         */
        private String operator;

        /**
         * 赋值类型 常量  变量
         */
        private AssignTypeEnum assignType;

        /**
         * 值或者变量code
         */
        private Object value;

        public String getEnvKey() {
            return envKey;
        }

        public void setEnvKey(String envKey) {
            this.envKey = envKey;
        }

        public DataType getDataType() {
            return dataType;
        }

        public void setDataType(DataType dataType) {
            this.dataType = dataType;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public AssignTypeEnum getAssignType() {
            return assignType;
        }

        public void setAssignType(AssignTypeEnum assignType) {
            this.assignType = assignType;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    public enum ConditionType {
        DEFAULT,
        CUSTOM
    }

}
