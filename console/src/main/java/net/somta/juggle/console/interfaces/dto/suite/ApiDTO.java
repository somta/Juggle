package net.somta.juggle.console.interfaces.dto.suite;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author husong
 */
public class ApiDTO {

    private Integer id;

    private String suiteName;

    private String apiUrl;

    private String apiName;

    private String apiDesc;

    private String apiRequestType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ApiDTO{" +
                "id=" + id +
                ", suiteName='" + suiteName + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", apiName='" + apiName + '\'' +
                ", apiDesc='" + apiDesc + '\'' +
                ", apiRequestType='" + apiRequestType + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
