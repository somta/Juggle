package net.somta.juggle.console.enums.error;

import net.somta.core.base.IBaseError;
import net.somta.juggle.console.contants.ApplicationContants;

public enum FlowDefinitionErrorEnum implements IBaseError {
    FLOW_PARAM_ERROR(1000,  "流程参数不能为空"),
    FLOW_DEFINITION_NOT_EXIST(1001,  "流程定义不存在"),
    FLOW_DEFINITION_ID_IS_NULL_ERROR(1002,  "流程定义ID不能为空");

    private int errorCode;
    private String errorMsg;

    FlowDefinitionErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationContants.FLOW_DEFINITION_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
