package net.somta.juggle.console.domain.datatype.enums;

public enum DataTypeClassifyEnum {

    Basics(1,"基础类型"),
    collection(2,"集合类型"),
    object(3,"高级类型");

    private int code;
    private String name;

    DataTypeClassifyEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据code获取枚举的name属性
     * @param code
     * @return
     */
    public static String getName(Integer code) {
        DataTypeClassifyEnum result = null;
        for (DataTypeClassifyEnum classify : values()) {
            if (classify.getCode() == code) {
                result = classify;
                break;
            }
        }
        return result.getName();
    }


    /**
     * 根据code获取枚举的Key
     * @param code
     * @return
     */
    public static String getKey(Integer code) {
        DataTypeClassifyEnum result = null;
        for (DataTypeClassifyEnum classify : values()) {
            if (classify.getCode() == code) {
                result = classify;
                break;
            }
        }
        return result.name();
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
