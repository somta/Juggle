package net.somta.juggle.console.interfaces.param.suite;

import net.somta.core.base.page.PageParam;

/**
 * @author husong
 * @since 1.1.0
 */
public class SuiteQueryParam extends PageParam {
    private String suiteName;

    private Integer suiteFlag;

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public Integer getSuiteFlag() {
        return suiteFlag;
    }

    public void setSuiteFlag(Integer suiteFlag) {
        this.suiteFlag = suiteFlag;
    }
}
