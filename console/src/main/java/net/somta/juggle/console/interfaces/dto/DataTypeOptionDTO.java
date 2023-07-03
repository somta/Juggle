package net.somta.juggle.console.interfaces.dto;

import java.util.List;

public class DataTypeOptionDTO {
    private String key;
    private String name;
    private List<DataTypeDTO> children;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataTypeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<DataTypeDTO> children) {
        this.children = children;
    }
}
