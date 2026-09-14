package com.matecoder.juggle.console.domain.system.datasource.enums;

import com.matecoder.core.base.IBaseError;
import com.matecoder.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 * @since 1.2.0
 */
public enum DatasourceErrorEnum implements IBaseError {

    DATASOURCE_CONNECT_ERROR(1000,  "数据源连接失败"),
    ;

    private int errorCode;
    private String errorMsg;

    DatasourceErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationConstants.DATASOURCE_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
