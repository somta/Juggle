package net.somta.juggle.console.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author husong
 */
public class DomainDTO {
    private Long id;
    private String domainCode;
    private String domainName;
    private String domainDesc;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainCode() {
        return domainCode;
    }

    public void setDomainCode(String domainCode) {
        this.domainCode = domainCode;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainDesc() {
        return domainDesc;
    }

    public void setDomainDesc(String domainDesc) {
        this.domainDesc = domainDesc;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "DomainDTO{" +
                "id=" + id +
                ", domainCode='" + domainCode + '\'' +
                ", domainName='" + domainName + '\'' +
                ", domainDesc='" + domainDesc + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
