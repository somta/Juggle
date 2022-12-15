package net.somta.juggle.core.model;

/**
 * 参数基类
 * @author husong
 * @date 2022/12/14
 **/
public class BaseParameter {
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数类型 String,Boolean,int
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
