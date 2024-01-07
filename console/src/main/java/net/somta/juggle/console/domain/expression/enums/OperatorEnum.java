package net.somta.juggle.console.domain.expression.enums;

/**
 * @author husong
 */
public enum OperatorEnum {
    EQUAL("equal","等于"),
    NOT_EQUAL("notEqual","不等于"),
    EMPTY("empty","为空"),
    NOT_EMPTY("notEmpty","不为空"),
    CONTAINS("contains","包含"),
    NOT_CONTAINS("notContains","不包含");

    private String code;
    private String desc;

    OperatorEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
}
