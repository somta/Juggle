package net.somta.juggle.console.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import net.somta.juggle.core.model.DataTypeInfo;

public class VariableParam {
    /**
     * 变量Key
     */
    private String key;

    /**
     * 变量名称
     */
    private String name;

    /**
     * 数据类型
     */
    @Schema
    private DataTypeInfo dataType;

    /**
     * 流程ID
     */
    private Integer flowId;

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

    public DataTypeInfo getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeInfo dataType) {
        this.dataType = dataType;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }
}
