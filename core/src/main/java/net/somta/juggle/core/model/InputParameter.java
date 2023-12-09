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

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}
