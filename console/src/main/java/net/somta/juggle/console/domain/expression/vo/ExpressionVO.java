package net.somta.juggle.console.domain.expression.vo;

import net.somta.juggle.console.domain.expression.enums.OperatorEnum;

/**
 * @author husong
 */
public class ExpressionVO {
    /**
     * 原变量key
     */
    private String envKey;

    /**
     * 原变量的类型
     */
    private String dataType;

    /**
     * 操作符 等于 不等于
     */
    private OperatorEnum operator;

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public OperatorEnum getOperator() {
        return operator;
    }

    public void setOperator(OperatorEnum operator) {
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
