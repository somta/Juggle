package net.somta.juggle.console.infrastructure.po;

import net.somta.core.base.BaseModel;

/**
 * @author husong
 */
public class ParameterPO extends BaseModel {
    /**
     *
     */
    private Long id;

    /**
     * 参数类型 1：入参  2：出参
     */
    private Integer paramType;

    /**
     * 参数Key
     */
    private String paramKey;

    /**
     * 参数名称
     */
    private String paramName;

    private String paramDesc;

    /**
     * 参数的数据类型
     */
    private String dataType;

    /**
     * 是否必填  0：非必填  1：必填
     */
    private Boolean required;

    /**
     * 来源类型  api：接口   flow：流程
     */
    private String sourceType;

    private Long sourceId;

    private Integer deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

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

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
