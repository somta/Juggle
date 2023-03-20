package net.somta.juggle.console.model;

public class DataTypeInfo {

    private Integer id;

    /**
     * 数据类型分类 1：基础类型  2：集合类型   3：高级类型
     */
    private Integer dataTypeClassify;

    private String type;

    private String typeName;

    private String displayName;

    private String objectStructure;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getObjectStructure() {
        return objectStructure;
    }

    public void setObjectStructure(String objectStructure) {
        this.objectStructure = objectStructure;
    }
}
