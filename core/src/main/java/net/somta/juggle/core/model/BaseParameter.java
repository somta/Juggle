package net.somta.juggle.core.model;

/**
 * 参数基类
 * @author husong
 * @date 2022/12/14
 **/
public class BaseParameter {
    /**
     * 参数Key,用于程序识别
     */
    private String key;
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数类型
     */
    private DataType dataType;

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

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
