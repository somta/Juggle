package net.somta.juggle.console.interfaces.param.system;

import net.somta.core.base.page.PageParam;

/**
 * @author husong
 * @since 1.2.0
 */
public class DataSourceQueryParam extends PageParam {
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
