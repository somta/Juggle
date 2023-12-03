package net.somta.juggle.console.domain.parameter.enums;

/**
 * @author husong
 */
public enum ParameterTypeEnum {
    INPUT_PARAM(1,"入参"),
    OUTPUT_PARAM(2,"出参"),
    PROPERTY(3,"属性");

    private int code;
    private String name;

    ParameterTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }
}
