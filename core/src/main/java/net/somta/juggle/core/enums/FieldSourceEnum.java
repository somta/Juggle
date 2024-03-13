package net.somta.juggle.core.enums;

/**
 * 字段的数据来源枚举
 * @author husong
 * @since 1.0.0
 */
public enum FieldSourceEnum {
    /**
     * 变量
     */
    VARIABLE,
    /**
     * 请求头
     */
    HEADER,
    /**
     * 入参
     */
    INPUT_PARAM,
    /**
     * 出参
     */
    OUTPUT_PARAM
}