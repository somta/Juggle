package net.somta.juggle.console.interfaces.param.suite;

import net.somta.core.base.page.PageParam;

/**
 * @author husong
 * @since 1.2.2
 */
public class SuiteMarketQueryParam extends PageParam {
    private String suiteName;
    private Long suiteClassifyId;
    private Integer priceStatus;

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public Long getSuiteClassifyId() {
        return suiteClassifyId;
    }

    public void setSuiteClassifyId(Long suiteClassifyId) {
        this.suiteClassifyId = suiteClassifyId;
    }

    public Integer getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(Integer priceStatus) {
        this.priceStatus = priceStatus;
    }
}
