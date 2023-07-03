package net.somta.juggle.console.interfaces.param;

import net.somta.core.base.page.PageParam;

public class DomainQueryParam extends PageParam {
    private String domainName;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
