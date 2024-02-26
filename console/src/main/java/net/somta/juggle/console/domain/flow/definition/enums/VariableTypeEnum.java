package net.somta.juggle.console.domain.flow.definition.enums;

/**
 * @author husong
 */
public enum VariableTypeEnum {
    INPUT_PARAM_VARIABLE(1,"入参变量"),
    OUTPUT_PARAM_VARIABLE(2,"出参变量"),
    MIDDLE_VARIABLE(3,"中间变量");

    private int code;
    private String name;

    VariableTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }
}
