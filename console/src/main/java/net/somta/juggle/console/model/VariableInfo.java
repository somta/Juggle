package net.somta.juggle.console.model;

import net.somta.core.base.BaseModel;
import net.somta.juggle.core.model.DataType;

public class VariableInfo extends BaseModel {

    /**
     * 唯一ID
     */
    private Integer id;

    /**
     * 流程定义ID
     */
    private Integer flowDefinitionId;

    /**
     * 变量Key,同一流程内唯一
     */
    private String key;

    /**
     * 变量名称
     */
    private String name;

    /**
     * 数据类型
     */
    private DataType dataType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlowDefinitionId() {
        return flowDefinitionId;
    }

    public void setFlowDefinitionId(Integer flowDefinitionId) {
        this.flowDefinitionId = flowDefinitionId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
