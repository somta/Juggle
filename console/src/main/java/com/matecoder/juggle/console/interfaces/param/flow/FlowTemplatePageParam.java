package com.matecoder.juggle.console.interfaces.param.flow;

import com.matecoder.core.base.page.PageParam;

/**
 * @author husong
 */
public class FlowTemplatePageParam extends PageParam {

    private String templateName;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
