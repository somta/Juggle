package net.somta.juggle.console.interfaces.param;

public class OutputParameterParam {

    private String paramKey;
    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 参数的数据类型
     */
    private String dataType;

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
