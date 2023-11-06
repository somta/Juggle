package net.somta.juggle.console.interfaces.dto;

/**
 * @author husong
 */
public class VariableInfoDTO {

    /**
     * 唯一ID
     */
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

    @Override
    public String toString() {
        return "VariableInfoDTO{" +
                "id=" + id +
                ", envKey='" + envKey + '\'' +
                ", envName='" + envName + '\'' +
                ", envType=" + envType +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
