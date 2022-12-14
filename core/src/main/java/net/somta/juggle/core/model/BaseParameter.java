package net.somta.juggle.core.model;

public class BaseParameter {
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数类型
     */
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
