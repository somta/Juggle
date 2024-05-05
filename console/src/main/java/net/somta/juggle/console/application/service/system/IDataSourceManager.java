package net.somta.juggle.console.application.service.system;

import net.somta.juggle.core.executor.data.IDataSource;
import net.somta.juggle.core.model.DataSource;

/**
 * @author husong
 * @since 1.2.0
 */
public interface IDataSourceManager extends IDataSource {

    void addDataSourceToCache(DataSource dataSource);

    void deleteDataSourceFromCache(Long dataSourceId);

}
