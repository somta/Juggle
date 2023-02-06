package net.somta.juggle.core.model;

import net.somta.juggle.core.enums.DataTypeEnum;

/**
 * 数据类型描述类
 *
 * @author husong
 * @date 2023/02/06
 */
public class DataTypeInfo {

    /**
     * 数据类型
     */
    private DataTypeEnum type;
    /**
     * 集合的泛型类型
     */
    private String itemType;

    /**
     * 结构对象的Schema描述
     */
    private String structureSchema;

    public DataTypeInfo() {
    }

    public DataTypeInfo(DataTypeEnum type) {
        this.type = type;
    }

    public DataTypeEnum getType() {
        return type;
    }

    public void setType(DataTypeEnum type) {
        this.type = type;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getStructureSchema() {
        return structureSchema;
    }

    public void setStructureSchema(String structureSchema) {
        this.structureSchema = structureSchema;
    }
}
