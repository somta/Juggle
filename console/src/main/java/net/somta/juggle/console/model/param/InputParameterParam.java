package net.somta.juggle.console.model.param;

public class InputParameterParam {

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
    private Integer required;

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

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }
}
