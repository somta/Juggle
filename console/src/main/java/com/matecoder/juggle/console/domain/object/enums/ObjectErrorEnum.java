package com.matecoder.juggle.console.domain.object.enums;

import com.matecoder.core.base.IBaseError;
import com.matecoder.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 */
public enum ObjectErrorEnum implements IBaseError {
    OBJECT_KEY_EXIST(1000,  "对象编码已存在"),
    ;

    private int errorCode;
    private String errorMsg;

    ObjectErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationConstants.OBJECT_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}