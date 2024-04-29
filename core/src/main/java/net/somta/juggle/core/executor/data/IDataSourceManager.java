package net.somta.juggle.core.executor.data;

import net.somta.juggle.core.model.DataSource;

public interface IDataSourceManager {

    void addDataSourceToCache(DataSource dataSource);

    void deleteDataSourceFromCache(Long dataSourceId);

    DataSource getDataSource(Long dataSourceId);
}
