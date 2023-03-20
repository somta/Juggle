package net.somta.juggle.console.model.dto;

import net.somta.juggle.console.model.DataTypeInfo;

import java.util.List;

public class DataTypeOptionDTO {
    private String lable;
    private List<DataTypeInfo> childs;

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public List<DataTypeInfo> getChilds() {
        return childs;
    }

    public void setChilds(List<DataTypeInfo> childs) {
        this.childs = childs;
    }
}
