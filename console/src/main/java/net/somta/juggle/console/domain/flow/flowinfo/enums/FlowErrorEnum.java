package net.somta.juggle.console.domain.flow.flowinfo.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 */
public enum FlowErrorEnum implements IBaseError {
    FLOW_KEY_IS_EMPTY(1000,  "流程KEY不能为空"),
    FLOW_NOT_EXIST(1001,  "流程不存在"),
    ;

    private int errorCode;
    private String errorMsg;

    FlowErrorEnum(int errorCode, String errorMsg) {
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
