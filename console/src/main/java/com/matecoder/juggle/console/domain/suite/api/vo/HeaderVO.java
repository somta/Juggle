package com.matecoder.juggle.console.domain.suite.api.vo;

import com.matecoder.juggle.core.model.DataType;

/**
 * @author Gavin
 */
public class HeaderVO {

    /**
     * header key
     */
    private String paramKey;
    /**
     * header 名称
     */
    private String paramName;

    /**
     * header 的数据类型
     */
    private DataType dataType;

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
