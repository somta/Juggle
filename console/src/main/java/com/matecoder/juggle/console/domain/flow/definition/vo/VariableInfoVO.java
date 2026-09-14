package com.matecoder.juggle.console.domain.flow.definition.vo;

import com.matecoder.juggle.core.model.DataType;

/**
 * @author husong
 */
public class VariableInfoVO {

    /**
     * variable id
     */
    private Long id;
    /**
     * 变量Key,同一流程内唯一
     */
    private String variableKey;

    /**
     * variable name
     */
    private String variableName;

    /**
     * variable type 1：入参变量   2：出参变量  3：中间变量
     */
    private Integer variableType;

    /**
     * data type
     */
    private DataType dataType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

}
