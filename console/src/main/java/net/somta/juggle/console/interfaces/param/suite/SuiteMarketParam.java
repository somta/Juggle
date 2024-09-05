package net.somta.juggle.console.interfaces.param.suite;

/**
 * @author husong
 * @since 1.2.1
 */
public class SuiteMarketParam {
    private Long suiteId;
    private String bill;

    public Long getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(Long suiteId) {
        this.suiteId = suiteId;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
