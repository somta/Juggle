package net.somta.juggle.core.model;

import net.somta.juggle.core.enums.DataTypeEnum;

/**
 * 变量类
 *
 * @author husong
 * @date 2023/01/04
 */
public class Variable {

    /**
     * 变量Key
     */
    private String key;

    /**
     * 变量名称
     */
    private String name;

    /**
     * 数据类型
     */
    private DataTypeInfo dataType;

    /**
     * todo 感觉变量不应该有默认值
     * 默认值
     */
    private String defaultValue;

    // todo 需要一个流程ID

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataTypeInfo getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeInfo dataType) {
        this.dataType = dataType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
