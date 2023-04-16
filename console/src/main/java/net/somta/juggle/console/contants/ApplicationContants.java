package net.somta.juggle.console.contants;

public class ApplicationContants {

    /**
     * 应用统一前缀
     */
    public final static int APPLICATION_CODE_PREFIX = 200;

    /**
     * 流程定义错误码
     */
    public final static long FLOW_DEFINITION_CODE = (APPLICATION_CODE_PREFIX * 1000 + 001) * 10000;

    /**
     * 流程错误码
     */
    public final static long FLOW_CODE = (APPLICATION_CODE_PREFIX * 1000 + 002) * 10000;
}
