package com.matecoder.juggle.console.domain.suite.suiteinfo.enums;

import com.matecoder.core.base.IBaseError;
import com.matecoder.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 */
public enum SuiteErrorEnum implements IBaseError {
    SUITE_EXIST_API_ERROR(1000,  "该套件下存在接口，无法删除"),
    SUITE_NOT_EXIST_ERROR(1001,  "套件不存在"),
    SUITE_IS_EXIST_ERROR(1002,  "套件%s已存在"),
    ;

    private int errorCode;
    private String errorMsg;

    SuiteErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationConstants.SUITE_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
