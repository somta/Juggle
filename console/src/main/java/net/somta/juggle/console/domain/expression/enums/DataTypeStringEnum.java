package net.somta.juggle.console.domain.expression.enums;

/**
 * @author husong
 */
public enum DataTypeStringEnum {

    INTEGER("Integer","整数"),
    STRING("String","字符串");

    private String code;
    private String desc;

    DataTypeStringEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
}
