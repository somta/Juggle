package net.somta.juggle.console.domain.suite.suiteinfo.enums;

/**
 * @author husong
 */
public enum SuiteTypeEnum {
    INSIDE_SUITE(0,"内置套件"),
    OFFICIAL_SUITE(1,"官方套件"),
    PERSONAL_SUITE(2,"个人套件");

    private int code;
    private String desc;

    SuiteTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }
}
