package net.somta.juggle.console.domain.suite.suiteinfo.vo;

import java.util.List;

public class SuiteMarketInfoVO {

    private Long id;

    private String suiteCode;

    private String suiteName;

    private Integer suiteType;

    private String suiteImage;

    private String suiteVersion;

    private String suiteDesc;

    private Integer priceStatus;

    private Double suitePrice;

    private String suiteHelpDocJson;

    private List<ApiInfoVO> apiList;

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

    public Integer getSuiteType() {
        return suiteType;
    }

    public void setSuiteType(Integer suiteType) {
        this.suiteType = suiteType;
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

    public Integer getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(Integer priceStatus) {
        this.priceStatus = priceStatus;
    }

    public Double getSuitePrice() {
        return suitePrice;
    }

    public void setSuitePrice(Double suitePrice) {
        this.suitePrice = suitePrice;
    }

    public String getSuiteHelpDocJson() {
        return suiteHelpDocJson;
    }

    public void setSuiteHelpDocJson(String suiteHelpDocJson) {
        this.suiteHelpDocJson = suiteHelpDocJson;
    }

    public List<ApiInfoVO> getApiList() {
        return apiList;
    }

    public void setApiList(List<ApiInfoVO> apiList) {
        this.apiList = apiList;
    }

    public static class ApiInfoVO {

        private String apiUrl;

        private String apiName;

        private String apiDesc;

        private String apiRequestType;

        private String apiRequestContentType;

        private String apiHeaders;

        private String apiInputParams;

        private String apiOutputParams;

        public String getApiUrl() {
            return apiUrl;
        }

        public void setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
        }

        public String getApiName() {
            return apiName;
        }

        public void setApiName(String apiName) {
            this.apiName = apiName;
        }

        public String getApiDesc() {
            return apiDesc;
        }

        public void setApiDesc(String apiDesc) {
            this.apiDesc = apiDesc;
        }

        public String getApiRequestType() {
            return apiRequestType;
        }

        public void setApiRequestType(String apiRequestType) {
            this.apiRequestType = apiRequestType;
        }

        public String getApiRequestContentType() {
            return apiRequestContentType;
        }

        public void setApiRequestContentType(String apiRequestContentType) {
            this.apiRequestContentType = apiRequestContentType;
        }

        public String getApiHeaders() {
            return apiHeaders;
        }

        public void setApiHeaders(String apiHeaders) {
            this.apiHeaders = apiHeaders;
        }

        public String getApiInputParams() {
            return apiInputParams;
        }

        public void setApiInputParams(String apiInputParams) {
            this.apiInputParams = apiInputParams;
        }

        public String getApiOutputParams() {
            return apiOutputParams;
        }

        public void setApiOutputParams(String apiOutputParams) {
            this.apiOutputParams = apiOutputParams;
        }
    }



}
