package net.somta.juggle.console.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import net.somta.juggle.core.model.DataType;

@Schema(description = "变量入参对象")
public class VariableParam {
    /**
     * 唯一ID
     */
    private Long id;
    /**
     * 变量Key
     */
    @Schema(description = "变量Key")
    private String envKey;

    /**
     * 变量名称
     */
    @Schema(description = "变量名称")
    private String envName;

    /**
     * 数据类型
     */
    @Schema(description = "变量类型对象")
    private DataType dataType;

    /**
     * 流程定义ID
     */
    @Schema(description = "流程定义ID")
    private Integer flowDefinitionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Integer getFlowDefinitionId() {
        return flowDefinitionId;
    }

    public void setFlowDefinitionId(Integer flowDefinitionId) {
        this.flowDefinitionId = flowDefinitionId;
    }
}
