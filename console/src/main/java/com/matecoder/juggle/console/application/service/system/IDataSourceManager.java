package com.matecoder.juggle.console.application.service.system;

import com.matecoder.juggle.core.executor.data.IDataSource;
import com.matecoder.juggle.core.model.DataSource;

/**
 * @author husong
 * @since 1.2.0
 */
public interface IDataSourceManager extends IDataSource {

    void deleteDataSourceFromCache(Long dataSourceId);

}
