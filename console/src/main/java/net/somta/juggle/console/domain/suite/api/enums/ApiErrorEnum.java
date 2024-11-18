package net.somta.juggle.console.domain.suite.api.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 */
public enum ApiErrorEnum implements IBaseError {
    API_NOT_EXIST(1000,  "api接口不存在"),
    API_NOT_EDIT_ERROR(1001,  "不能修改官方套件下的接口"),
    ;

    private int errorCode;
    private String errorMsg;

    ApiErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationConstants.API_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
