package net.somta.juggle.console.domain.flow.definition.vo;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.DataType;

/**
 * @author husong
 */
public class VariableInfoVO {
    private Long id;

    /**
     * 变量Key,同一流程内唯一
     */
    private String envKey;

    /**
     * variable name
     */
    private String envName;

    /**
     * variable type 1：入参变量   2：出参变量  3：中间变量
     */
    private Integer envType;

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

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

}
