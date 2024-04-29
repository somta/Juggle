package net.somta.juggle.console.domain.system.datasource.vo;

/**
 * @author husong
 * @since 1.2.0
 */
public class DataSourceQueryVO {
    private String dataSourceName;
    private String dataSourceType;

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
}
