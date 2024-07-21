package net.somta.juggle.console.interfaces.dto;

import net.somta.juggle.core.model.Property;

import java.util.List;

/**
 * @author husong
 */
public class DataTypeOptionDTO {
    private Long id;

    private Integer dataTypeClassify;

    private String type;

    private String displayName;

    private String objectKey;

    private List<Property> objectStructure;

    public DataTypeOptionDTO() {
    }

    public DataTypeOptionDTO(Long id, Integer dataTypeClassify, String type, String displayName) {
        this.id = id;
        this.dataTypeClassify = dataTypeClassify;
        this.type = type;
        this.displayName = displayName;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<Property> getObjectStructure() {
        return objectStructure;
    }

    public void setObjectStructure(List<Property> objectStructure) {
        this.objectStructure = objectStructure;
    }
}
