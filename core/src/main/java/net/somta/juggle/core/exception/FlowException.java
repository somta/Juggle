package net.somta.juggle.core.exception;

import net.somta.juggle.core.enums.ErrorEnum;

public class FlowException extends Exception{

    private int errCode;
    private String errMsg;

    public FlowException(ErrorEnum errorEnum) {
        super(errorEnum.getErrMsg());
        this.errCode = errorEnum.getErrCode();
        this.errMsg = errorEnum.getErrMsg();
    }
}
