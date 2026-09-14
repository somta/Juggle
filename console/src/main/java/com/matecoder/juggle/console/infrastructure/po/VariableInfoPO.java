package com.matecoder.juggle.console.infrastructure.po;

import com.matecoder.core.base.BaseModel;

/**
 * @author husong
 */
public class VariableInfoPO extends BaseModel {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 流程定义ID
     */
    private Long flowDefinitionId;

    /**
     * 变量Key,同一流程内唯一
     */
    private String variableKey;

    /**
     * variable name
     */
    private String variableName;

    /**
     * variable type 1：入参变量   2：出参变量 3:中间变量
     */
    private Integer variableType;

    /**
     * data type
     */
    private String dataType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlowDefinitionId() {
        return flowDefinitionId;
    }

    public void setFlowDefinitionId(Long flowDefinitionId) {
        this.flowDefinitionId = flowDefinitionId;
    }

    public String getVariableKey() {
        return variableKey;
    }

    public void setVariableKey(String variableKey) {
        this.variableKey = variableKey;
    }

    public Integer getVariableType() {
        return variableType;
    }

    public void setVariableType(Integer variableType) {
        this.variableType = variableType;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
