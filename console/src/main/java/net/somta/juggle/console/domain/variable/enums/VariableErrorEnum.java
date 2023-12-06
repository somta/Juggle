package net.somta.juggle.console.domain.variable.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 */
public enum VariableErrorEnum implements IBaseError {
    VARIABLE_PARAM_IS_NULL_ERROR(1000,  "变量参数不能为空"),
    VARIABLE_ID_IS_NULL_ERROR(1000,  "变量ID不能为空"),
    ;

    private int errorCode;
    private String errorMsg;

    VariableErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationConstants.VARIABLE_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}