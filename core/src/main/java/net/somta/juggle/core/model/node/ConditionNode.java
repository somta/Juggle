package net.somta.juggle.core.model.node;

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
    }

    public enum ConditionType {
        DEFAULT,
        CUSTOM
    }

}
