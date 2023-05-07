package net.somta.juggle.console.model.param;

public class InputParameterParam {


    private String paramKey;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 参数的数据类型
     */
    private String dataType;

    /**
     * 是否必填  0：非必填  1：必填
     */
    private Boolean required;

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

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}
