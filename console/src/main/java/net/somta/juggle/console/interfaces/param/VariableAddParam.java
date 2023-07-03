package net.somta.juggle.console.interfaces.param;

import io.swagger.v3.oas.annotations.media.Schema;
import net.somta.juggle.core.model.DataType;

@Schema(description = "变量入参对象")
public class VariableAddParam {
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
    private Long flowDefinitionId;

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

    public Long getFlowDefinitionId() {
        return flowDefinitionId;
    }

    public void setFlowDefinitionId(Long flowDefinitionId) {
        this.flowDefinitionId = flowDefinitionId;
    }
}
