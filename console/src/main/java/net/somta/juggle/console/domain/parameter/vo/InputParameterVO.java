package net.somta.juggle.console.domain.parameter.vo;

import net.somta.juggle.core.model.DataType;

/**
 * @author Gavin
 */
public class InputParameterVO {

    /**
     * 参数key
     */
    private String paramKey;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * parameter position
     */
    private String paramPosition;

    /**
     * 参数的数据类型
     */
    private DataType dataType;

    /**
     * 是否必填  0：非必填  1：必填
     */
    private Boolean required;

    private String paramDesc;

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

    public String getParamPosition() {
        return paramPosition;
    }

    public void setParamPosition(String paramPosition) {
        this.paramPosition = paramPosition;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }
}
