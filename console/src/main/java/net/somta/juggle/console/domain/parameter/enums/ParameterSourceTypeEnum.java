package net.somta.juggle.console.domain.parameter.enums;

public enum ParameterSourceTypeEnum {
    FLOW("flow","流程参数"),
    API("api","接口参数");

    private String code;
    private String name;

    ParameterSourceTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
}
