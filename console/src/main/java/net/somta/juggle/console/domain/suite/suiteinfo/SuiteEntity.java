package net.somta.juggle.console.domain.suite.suiteinfo;

/**
 * @author husong
 * @since 1.1.1
 */
public class SuiteEntity {
    private Long id;
    private String suiteCode;
    private String suiteName;
    private String suiteImage;
    private String suiteVersion;
    private String suiteDesc;
    private String suiteHelpDocJson;
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

    public String getSuiteVersion() {
        return suiteVersion;
    }

    public void setSuiteVersion(String suiteVersion) {
        this.suiteVersion = suiteVersion;
    }

    public String getSuiteDesc() {
        return suiteDesc;
    }

    public void setSuiteDesc(String suiteDesc) {
        this.suiteDesc = suiteDesc;
    }

    public String getSuiteHelpDocJson() {
        return suiteHelpDocJson;
    }

    public void setSuiteHelpDocJson(String suiteHelpDocJson) {
        this.suiteHelpDocJson = suiteHelpDocJson;
    }

    public Integer getSuiteFlag() {
        return suiteFlag;
    }

    public void setSuiteFlag(Integer suiteFlag) {
        this.suiteFlag = suiteFlag;
    }
}
