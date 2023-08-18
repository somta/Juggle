package net.somta.juggle.console.domain.flow.enums;

public enum FlowStatusEnum {

    DISABLED(0,"禁用"),
    ENABLE(1,"启用");

    private Integer code;
    private String desc;


    FlowStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
