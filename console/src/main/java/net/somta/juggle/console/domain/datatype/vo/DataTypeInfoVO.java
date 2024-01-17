package net.somta.juggle.console.domain.datatype.vo;

/**
 * @author husong
 */
public class DataTypeInfoVO {

    private Long id;
    private Integer dataTypeClassify;
    private String dataType;

    private String displayName;

    private String objectKey;

    private String objectStructure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDataTypeClassify() {
        return dataTypeClassify;
    }

    public void setDataTypeClassify(Integer dataTypeClassify) {
        this.dataTypeClassify = dataTypeClassify;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getObjectStructure() {
        return objectStructure;
    }

    public void setObjectStructure(String objectStructure) {
        this.objectStructure = objectStructure;
    }
}
