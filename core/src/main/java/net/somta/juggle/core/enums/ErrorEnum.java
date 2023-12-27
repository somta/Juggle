package net.somta.juggle.core.enums;

/**
 * @author husong
 */
public enum ErrorEnum {

    ENV_KEY_ERROR(1001, "变量的key不能为空"),
    FLOW_ELEMENT_IS_EMPTY_ERROR(1002, "流程元素不能为空")
    ;


    private int errCode;
    private String errMsg;

    ErrorEnum(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
