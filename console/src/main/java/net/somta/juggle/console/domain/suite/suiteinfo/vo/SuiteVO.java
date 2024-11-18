package net.somta.juggle.console.domain.suite.suiteinfo.vo;

/**
 * @author husong
 * @since 1.1.1
 */
public class SuiteVO {
    private Long id;
    private String suiteCode;
    private String suiteName;
    private String suiteImage;
    private String suiteDesc;
    private Integer suiteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuiteCode() {
        return suiteCode;
    }

    public void setSuiteCode(String suiteCode) {
        this.suiteCode = suiteCode;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public String getSuiteImage() {
        return suiteImage;
    }

    public void setSuiteImage(String suiteImage) {
        this.suiteImage = suiteImage;
    }

    public String getSuiteDesc() {
        return suiteDesc;
    }

    public void setSuiteDesc(String suiteDesc) {
        this.suiteDesc = suiteDesc;
    }

    public Integer getSuiteFlag() {
        return suiteFlag;
    }

    public void setSuiteFlag(Integer suiteFlag) {
        this.suiteFlag = suiteFlag;
    }
}
