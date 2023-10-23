package net.somta.juggle.console.domain.version.enums;

/**
 * @author husong
 */

public enum FlowVersionStatusEnum {

    DISABLED(0,"禁用"),
    ENABLE(1,"启用");

    private Integer code;
    private String desc;


    FlowVersionStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FlowVersionStatusEnum getByCode(Integer code){
        for (FlowVersionStatusEnum status : FlowVersionStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }
}
