package net.somta.juggle.console.domain.suite.suiteinfo.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 */
public enum SuiteErrorEnum implements IBaseError {
    SUITE_EXIST_API_ERROR(1000,  "该套件下存在接口，无法删除"),
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
