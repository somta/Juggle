package net.somta.juggle.console.domain.template.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 */
public enum TemplateErrorEnum implements IBaseError {
    TEMPLATE_NOT_EXIST_ERROR(1001,  "模板不存在"),
    ;

    private int errorCode;
    private String errorMsg;

    TemplateErrorEnum(int errorCode, String errorMsg) {
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
