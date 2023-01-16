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
    private DataTypeEnum dataType;

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

    public DataTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEnum dataType) {
        this.dataType = dataType;
    }
}
