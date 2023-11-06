package net.somta.juggle.console.infrastructure.po;

import net.somta.core.base.BaseModel;

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
    private String envKey;

    /**
     * variable name
     */
    private String envName;

    /**
     * variable type 1：入参变量   2：出参变量 3:中间变量
     */
    private Integer envType;

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

    public String getEnvKey() {
        return envKey;
    }

    public void setEnvKey(String envKey) {
        this.envKey = envKey;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public Integer getEnvType() {
        return envType;
    }

    public void setEnvType(Integer envType) {
        this.envType = envType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
