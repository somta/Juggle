package net.somta.juggle.console.domain.api.vo;

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
    private String dataType;

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
