package net.somta.juggle.console.interfaces.param.market;

/**
 * @author husong
 * @since 1.2.3
 */
public class TemplateMarketParam {
    private Long templateId;
    private String bill;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
