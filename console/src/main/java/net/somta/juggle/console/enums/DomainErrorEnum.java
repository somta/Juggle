package net.somta.juggle.console.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.console.contants.ApplicationContants;

public enum DomainErrorEnum implements IBaseError {
    DOMAIN_EXIST_API_ERROR(1000,  "该领域下存在接口，不能删除"),
    ;

    private int errorCode;
    private String errorMsg;

    DomainErrorEnum(int errorCode, String errorMsg) {
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
