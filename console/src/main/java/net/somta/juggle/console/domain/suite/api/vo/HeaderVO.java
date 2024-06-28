package net.somta.juggle.console.domain.suite.api.vo;

import net.somta.juggle.core.model.DataType;

/**
 * @author Gavin
 */
public class HeaderVO {

    /**
     * header key
     */
    private String headerKey;
    /**
     * header 名称
     */
    private String headerName;

    /**
     * header 的数据类型
     */
    private DataType dataType;

    private Boolean required;

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
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
}
