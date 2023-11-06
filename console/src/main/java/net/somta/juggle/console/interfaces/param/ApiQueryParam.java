package net.somta.juggle.console.interfaces.param;

import net.somta.core.base.page.PageParam;

/**
 * @author husong
 */
public class ApiQueryParam extends PageParam {
    private Long domainId;

    private String apiName;

    private String apiUrl;

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
