package net.somta.juggle.console.model;

import net.somta.core.base.BaseModel;
import net.somta.juggle.core.model.DataType;

public class VariableInfo extends BaseModel {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 流程定义ID
     */
    private Integer flowDefinitionId;

    /**
     * 变量Key,同一流程内唯一
     */
    private String envKey;

    /**
     * 变量名称
     */
    private String envName;

    /**
     * 数据类型
     */
    private String dataType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFlowDefinitionId() {
        return flowDefinitionId;
    }

    public void setFlowDefinitionId(Integer flowDefinitionId) {
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
