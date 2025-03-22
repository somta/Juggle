package net.somta.juggle.console.domain.system.datasource;

import net.somta.common.encrypt.AESUtil;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;

public class DataSourceAO {
    private Long id;
    private String dataSourceName;
    private String dataSourceType;
    private String dataSourceDesc;
    private String address;
    private String port;
    private String userName;
    private String password;
    private String databaseName;
    private String connectExtInfo;

    /**
     * min connection count
     */
    private Integer minPoolSize;
    /**
     * max connection count
     */
    private Integer maxPoolSize;
    /**
     * query data timeout（seconds）
     */
    private Integer queryTimeout;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getDataSourceDesc() {
        return dataSourceDesc;
    }

    public void setDataSourceDesc(String dataSourceDesc) {
        this.dataSourceDesc = dataSourceDesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getConnectExtInfo() {
        return connectExtInfo;
    }

    public void setConnectExtInfo(String connectExtInfo) {
        this.connectExtInfo = connectExtInfo;
    }

    public Integer getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(Integer minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(Integer queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

}
