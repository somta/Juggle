package net.somta.juggle.core.expression.condition.enums;


/**
 * @author husong
 */
public enum OperatorEnum {
    EQUAL("equal","等于"),
    NOT_EQUAL("notEqual","不等于"),
    EMPTY("empty","为空"),
    NOT_EMPTY("notEmpty","不为空"),
    CONTAINS("contains","包含"),
    NOT_CONTAINS("notContains","不包含"),
    GREATER_THAN("greaterThan","大于"),
    NOT_LESS_THAN("notLessThan","大于等于"),
    LESS_THAN("lessThan","小于"),
    NOT_GREATER_THAN("notGreaterThan","小于等于");

    private String code;
    private String desc;

    OperatorEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
    public static OperatorEnum getByCode(String code){
        for (OperatorEnum operator : OperatorEnum.values()) {
            if (operator.getCode().equals(code)) {
                return operator;
            }
        }
        return null;
    }
}
