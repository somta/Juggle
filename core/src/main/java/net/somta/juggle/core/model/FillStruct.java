package net.somta.juggle.core.model;

import net.somta.juggle.core.enums.FieldSourceEnum;

/**
 * @author husong
 */
public class FillStruct {
    private String source;

    private FieldSourceEnum sourceType;

    private String sourceDataType;

    private String target;

    private FieldSourceEnum targetType;

    private String targetDataType;


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

    public String getSourceDataType() {
        return sourceDataType;
    }

    public void setSourceDataType(String sourceDataType) {
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

    public String getTargetDataType() {
        return targetDataType;
    }

    public void setTargetDataType(String targetDataType) {
        this.targetDataType = targetDataType;
    }
}
