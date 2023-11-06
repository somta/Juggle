package net.somta.juggle.console.domain.api.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.console.contants.ApplicationContants;

/**
 * @author husong
 */
public enum ApiErrorEnum implements IBaseError {
    API_NOT_EXIST(1000,  "api接口不存在"),
    ;

    private int errorCode;
    private String errorMsg;

    ApiErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationContants.API_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
