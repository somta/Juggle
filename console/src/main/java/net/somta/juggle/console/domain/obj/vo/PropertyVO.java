package net.somta.juggle.console.domain.obj.vo;

/**
 * @author Gavin
 */
public class PropertyVO {

    /**
     * 属性key
     */
    private String propKey;
    /**
     * 属性名称
     */
    private String propName;

    /**
     * 参数的数据类型
     */
    private String dataType;

    public String getPropKey() {
        return propKey;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
