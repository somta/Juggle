package net.somta.juggle.console.interfaces.param.suite;

import net.somta.core.base.page.PageParam;

/**
 * @author husong
 */
public class ApiQueryParam extends PageParam {
    private Long suiteId;

    private String apiName;

    private String apiUrl;

    public Long getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(Long suiteId) {
        this.suiteId = suiteId;
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
