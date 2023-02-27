package net.somta.juggle.core.model.node;

import java.util.LinkedHashMap;

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
    private LinkedHashMap<String,String> conditions;


    public LinkedHashMap<String, String> getConditions() {
        return conditions;
    }

    public void setConditions(LinkedHashMap<String, String> conditions) {
        this.conditions = conditions;
    }
}
