package net.somta.juggle.common.constants;

/**
 * @author husong
 */
public class ApplicationConstants {

    public static final String JUGGLE_SERVER_VERSION = "/v1";

    public static final String JUGGLE_API_PREFIX = "/api" + JUGGLE_SERVER_VERSION;

    public static final String JUGGLE_OPEN_API_PREFIX = "/open" + JUGGLE_SERVER_VERSION;

    public static final String COLON = ":";

    /**
     * 应用统一前缀
     */
    public final static int APPLICATION_CODE_PREFIX = 200;

    /**
     * 用户错误码
     */
    public final static long USER_CODE = (APPLICATION_CODE_PREFIX * 1000 + 1) * 10000;

    /**
     * 套件错误码
     */
    public final static long SUITE_CODE = (APPLICATION_CODE_PREFIX * 1000 + 2) * 10000;

    /**
     * 接口错误码
     */
    public final static long API_CODE = (APPLICATION_CODE_PREFIX * 1000 + 3) * 10000;

    /**
     * 变量错误码
     */
    public final static long VARIABLE_CODE = (APPLICATION_CODE_PREFIX * 1000 + 4) * 10000;

    /**
     * 流程定义错误码
     */
    public final static long FLOW_DEFINITION_CODE = (APPLICATION_CODE_PREFIX * 1000 + 5) * 10000;

    /**
     * 流程错误码
     */
    public final static long FLOW_CODE = (APPLICATION_CODE_PREFIX * 1000 + 6) * 10000;

    /**
     * 对象错误码
     */
    public final static long OBJECT_CODE = (APPLICATION_CODE_PREFIX * 1000 + 7) * 10000;

    /**
     * 数据源错误码
     */
    public final static long DATASOURCE_CODE = (APPLICATION_CODE_PREFIX * 1000 + 8) * 10000;
}
