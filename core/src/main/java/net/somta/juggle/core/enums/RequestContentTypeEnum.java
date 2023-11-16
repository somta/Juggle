package net.somta.juggle.core.enums;

/**
 * @author husong
 */
public enum RequestContentTypeEnum {

    APPLICATION_JSON("application/json"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded");

    private String value;

    RequestContentTypeEnum(String value) {
        this.value = value;
    }

    public static RequestContentTypeEnum findEnumByValue(String value){
        for (RequestContentTypeEnum e : RequestContentTypeEnum.values()) {
            if(e.getValue().equals(value)){
                return e;
            }
        }
        throw new IllegalArgumentException("requestContentType is not support or requestContentType is null!");
    }

    public String getValue() {
        return value;
    }
}
