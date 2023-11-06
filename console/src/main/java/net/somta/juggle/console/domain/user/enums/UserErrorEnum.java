package net.somta.juggle.console.domain.user.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.console.contants.ApplicationContants;

/**
 * @author husong
 */
public enum UserErrorEnum implements IBaseError {
    LOGIN_PARAM_ERROR(1000,  "用户名或密码不能为空"),
    USER_NOT_EXIST_ERROR(1001,  "用户不存在"),
    USER_PWD_ERROR(1002,  "用户密码错误"),
    USER_NOT_LOGIN_ERROR(1003,  "用户未登录"),
    OLD_PASSWORD_ERROR(1004,  "原密码错误"),
    ;

    private int errorCode;
    private String errorMsg;

    UserErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationContants.USER_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
