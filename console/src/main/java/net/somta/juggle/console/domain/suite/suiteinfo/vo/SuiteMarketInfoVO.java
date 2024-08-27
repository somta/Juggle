package net.somta.juggle.console.domain.suite.suiteinfo.vo;

import java.util.List;

public class SuiteMarketInfoVO {

    private Long id;
    /**
     * 套件编码
     */
    private String suiteCode;
    /**
     * 套件名称
     */
    private String suiteName;

    /**
     * 套件类型 1：官方套件  2：非官方套件
     */
    private Integer suiteType;

    /**
     * 套件头像
     */
    private String suiteImage;
    /**
     * 套件版本
     */
    private String suiteVersion;
    /**
     * 套件描述
     */
    private String suiteDesc;

    /**
     * 是否收费  0：免费  1:付费
     */
    private Integer priceStatus;

    /**
     * 套件价格
     */
    private Double suitePrice;

    /**
     * 套件的帮助文档JSON
     */
    private String suiteHelpDocJson;

    /**
     * 套件接口列表
     */
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
        private Long id;

        /**
         * api接口地址
         */
        private String apiUrl;

        /**
         * api接口名称
         */
        private String apiName;

        /**
         * api描述
         */
        private String apiDesc;

        /**
         * api请求类型  GET POST PUT
         */
        private String apiRequestType;

        /**
         * api请求内容类型 application/json
         */
        private String apiRequestContentType;

        /**
         * api请求头
         */
        private String apiHeaders;

        /**
         * api入参
         */
        private String apiInputParameter;
        /**
         * api出参
         */
        private String apiOutputParameter;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

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

        public String getApiInputParameter() {
            return apiInputParameter;
        }

        public void setApiInputParameter(String apiInputParameter) {
            this.apiInputParameter = apiInputParameter;
        }

        public String getApiOutputParameter() {
            return apiOutputParameter;
        }

        public void setApiOutputParameter(String apiOutputParameter) {
            this.apiOutputParameter = apiOutputParameter;
        }
    }



}
