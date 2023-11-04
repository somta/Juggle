package net.somta.juggle.console.contants;

/**
 * @author husong
 */
public class ApplicationContants {

    public static final String JUGGLE_SERVER_VERSION = "/v1";

    /**
     * 应用统一前缀
     */
    public final static int APPLICATION_CODE_PREFIX = 200;

    /**
     * 用户错误码
     */
    public final static long USER_CODE = (APPLICATION_CODE_PREFIX * 1000 + 001) * 10000;

    /**
     * 领域错误码
     */
    public final static long DOMAIN_CODE = (APPLICATION_CODE_PREFIX * 1000 + 002) * 10000;

    /**
     * 接口错误码
     */
    public final static long API_CODE = (APPLICATION_CODE_PREFIX * 1000 + 003) * 10000;

    /**
     * 变量错误码
     */
    public final static long VARIABLE_CODE = (APPLICATION_CODE_PREFIX * 1000 + 004) * 10000;

    /**
     * 流程定义错误码
     */
    public final static long FLOW_DEFINITION_CODE = (APPLICATION_CODE_PREFIX * 1000 + 005) * 10000;

    /**
     * 流程错误码
     */
    public final static long FLOW_CODE = (APPLICATION_CODE_PREFIX * 1000 + 006) * 10000;
}
