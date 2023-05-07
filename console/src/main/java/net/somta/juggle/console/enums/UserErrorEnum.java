package net.somta.juggle.console.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.console.contants.ApplicationContants;

public enum UserErrorEnum implements IBaseError {
    LOGIN_PARAM_ERROR(1000,  "用户名或密码不能为空"),
    USER_NOT_EXIST_ERROR(1000,  "用户不存在"),
    USER_PWD_ERROR(1000,  "用户密码错误");

    private int errorCode;
    private String errorMsg;

    UserErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationContants.FLOW_DEFINITION_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
