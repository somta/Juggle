package net.somta.juggle.console.enums;

public enum dataTypeClassifyEnum {

    Basics(1,"基础类型"),
    collection(2,"集合类型"),
    object(3,"高级类型");

    private int code;
    private String lable;

    dataTypeClassifyEnum(int code, String lable) {
        this.code = code;
        this.lable = lable;
    }
}
