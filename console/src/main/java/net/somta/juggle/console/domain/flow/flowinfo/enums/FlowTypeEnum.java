package net.somta.juggle.console.domain.flow.flowinfo.enums;

/**
 * @author husong
 */
public enum FlowTypeEnum {
    SYNC("sync","同步"),
    ASYNC("async","异步");

    private String code;
    private String desc;

    FlowTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

}
