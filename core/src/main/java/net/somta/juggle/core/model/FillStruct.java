package net.somta.juggle.core.model;

import net.somta.juggle.core.enums.FieldSourceEnum;

/**
 * @author husong
 */
public class FillStruct {
    private String source;

    private FieldSourceEnum sourceType;

    private DataType sourceDataType;

    private String target;

    private FieldSourceEnum targetType;

    private DataType targetDataType;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public FieldSourceEnum getSourceType() {
        return sourceType;
    }

    public void setSourceType(FieldSourceEnum sourceType) {
        this.sourceType = sourceType;
    }

    public DataType getSourceDataType() {
        return sourceDataType;
    }

    public void setSourceDataType(DataType sourceDataType) {
        this.sourceDataType = sourceDataType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public FieldSourceEnum getTargetType() {
        return targetType;
    }

    public void setTargetType(FieldSourceEnum targetType) {
        this.targetType = targetType;
    }

    public DataType getTargetDataType() {
        return targetDataType;
    }

    public void setTargetDataType(DataType targetDataType) {
        this.targetDataType = targetDataType;
    }
}
