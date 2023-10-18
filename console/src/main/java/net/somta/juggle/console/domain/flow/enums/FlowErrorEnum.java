package net.somta.juggle.console.domain.flow.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.console.contants.ApplicationContants;

public enum FlowErrorEnum implements IBaseError {
    FLOW_KEY_IS_EMPTY(1000,  "流程KEY不能为空"),
    FLOW_NOT_EXIST(1001,  "流程不存在"),
    ENABLE_FLOW_NOT_DELETE(1002,  "存在启用的流程版本，不能删除"),
    FLOW_NOT_ENABLE(1003,  "请启用流程后在调用流程"),
    ;

    private int errorCode;
    private String errorMsg;

    FlowErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationContants.FLOW_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
