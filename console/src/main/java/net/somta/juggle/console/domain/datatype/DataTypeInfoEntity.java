package net.somta.juggle.console.domain.datatype;

/**
 * @author husong
 */
public class DataTypeInfoEntity {

    /**
     * 数据类型分类 1：基础类型  2：集合类型   3：对象类型
     */
    private Integer dataTypeClassify;

    private String dataType;

    private String displayName;

    private String objectKey;

    private String objectStructure;


    public void buildObjectDataTypeInfoEntity(String objectKey, String objectStructure) {
        this.dataTypeClassify = 3;
        this.dataType = "object";
        this.objectKey = objectKey;
        this.objectStructure = objectStructure;
    }

    public Integer getDataTypeClassify() {
        return dataTypeClassify;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public String getObjectStructure() {
        return objectStructure;
    }
}
