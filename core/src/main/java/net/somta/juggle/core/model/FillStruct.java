package net.somta.juggle.core.model;

import net.somta.juggle.core.enums.FildSourceEnum;

public class FillStruct {
    private String source;

    private FildSourceEnum sourceType;

    private DataType sourceDataType;

    private String target;

    private FildSourceEnum targetType;

    private DataType targetDataType;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public FildSourceEnum getSourceType() {
        return sourceType;
    }

    public void setSourceType(FildSourceEnum sourceType) {
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

    public FildSourceEnum getTargetType() {
        return targetType;
    }

    public void setTargetType(FildSourceEnum targetType) {
        this.targetType = targetType;
    }

    public DataType getTargetDataType() {
        return targetDataType;
    }

    public void setTargetDataType(DataType targetDataType) {
        this.targetDataType = targetDataType;
    }
}
