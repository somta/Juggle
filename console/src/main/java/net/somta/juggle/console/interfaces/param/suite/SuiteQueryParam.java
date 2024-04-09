package net.somta.juggle.console.interfaces.param.suite;

import net.somta.core.base.page.PageParam;

/**
 * @author husong
 * @since 1.1.0
 */
public class SuiteQueryParam extends PageParam {
    private String suiteName;

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }
}
