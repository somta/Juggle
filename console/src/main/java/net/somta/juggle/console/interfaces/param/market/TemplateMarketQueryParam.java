package net.somta.juggle.console.interfaces.param.market;

import net.somta.core.base.page.PageParam;

/**
 * @author husong
 * @since 1.2.3
 */
public class TemplateMarketQueryParam extends PageParam {
    private String templateName;
    private Long templateClassifyId;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getTemplateClassifyId() {
        return templateClassifyId;
    }

    public void setTemplateClassifyId(Long templateClassifyId) {
        this.templateClassifyId = templateClassifyId;
    }
}