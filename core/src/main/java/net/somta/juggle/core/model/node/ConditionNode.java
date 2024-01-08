package net.somta.juggle.core.model.node;

import net.somta.juggle.core.enums.DataTypeEnum;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 判断节点
 *
 * @author husong
 * @date 2023/01/17
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
        private DataTypeEnum dataType;

        /**
         * 操作符 等于 不等于
         */
        private String operator;

        /**
         * 赋值类型 常量  变量
         */
        private String assignType;

        /**
         * 值或者变量code
         */
        private String value;

        public String getEnvKey() {
            return envKey;
        }

        public void setEnvKey(String envKey) {
            this.envKey = envKey;
        }

        public DataTypeEnum getDataType() {
            return dataType;
        }

        public void setDataType(DataTypeEnum dataType) {
            this.dataType = dataType;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getAssignType() {
            return assignType;
        }

        public void setAssignType(String assignType) {
            this.assignType = assignType;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum ConditionType {
        DEFAULT,
        CUSTOM
    }

}
