package net.somta.juggle.console.infrastructure.po.flow;

import net.somta.core.base.BaseModel;

/**
 * @author Gavin
 */
public class FlowTemplatePO extends BaseModel {
    private Long id;

    private String templateName;

    private String templateRemark;

    private String templateContent;

    private String flowType;

    private String inputs;

    private String outputs;

    private String variables;

    private Integer deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateRemark() {
        return templateRemark;
    }

    public void setTemplateRemark(String templateRemark) {
        this.templateRemark = templateRemark;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public String getOutputs() {
        return outputs;
    }

    public void setOutputs(String outputs) {
        this.outputs = outputs;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
