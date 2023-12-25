package net.somta.juggle.console.interfaces.dto.flow;

/**
 * @author Gavin
 */
public class FlowTemplateInfoDTO {
    private Long id;
    private String templateName;

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
}
