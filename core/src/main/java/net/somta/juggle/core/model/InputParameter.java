package net.somta.juggle.core.model;

/**
 * 入参类
 * @author husong
 * @date 2022/12/14
 **/
public class InputParameter extends BaseParameter {

    /**
     * 是否必填
     */
    private Boolean required;

    // todo 复杂类型的对象或自定义对象应该如何定义

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}
