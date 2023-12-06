package net.somta.juggle.console.domain.version.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 */
public enum FlowVersionErrorEnum implements IBaseError {
    ENABLE_FLOW_NOT_DELETE(1000,  "启用中的流程不能删除"),

    FLOW_NOT_ENABLE(1003,  "请启用流程后在调用流程"),
    ;

    private int errorCode;
    private String errorMsg;

    FlowVersionErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationConstants.FLOW_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
