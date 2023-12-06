package net.somta.juggle.console.domain.flow.definition.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 */
public enum FlowDefinitionErrorEnum implements IBaseError {
    FLOW_PARAM_ERROR(1000,  "流程参数不能为空"),
    FLOW_DEFINITION_NOT_EXIST(1001,  "流程定义不存在"),
    FLOW_DEFINITION_ID_IS_NULL_ERROR(1002,  "流程定义ID不能为空"),
    FLOW_DEFINITION_CONTENT_IS_NULL_ERROR(1003,  "流程定义内容不能为空");

    private int errorCode;
    private String errorMsg;

    FlowDefinitionErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationConstants.FLOW_DEFINITION_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
