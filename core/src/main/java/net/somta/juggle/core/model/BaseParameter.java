package net.somta.juggle.core.model;

import net.somta.juggle.core.enums.DataTypeEnum;

/**
 * 参数基类
 * @author husong
 * @date 2022/12/14
 **/
public class BaseParameter {
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数类型 String,Boolean,int
     */
    private DataTypeEnum dataType;

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
