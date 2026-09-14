package com.matecoder.juggle.console.domain.system.datasource.service;

import com.matecoder.juggle.core.model.DataSource;

/**
 * @author husong
 * @since 1.2.0
 */
public interface IDataSourceInstance {

    Object getDataSourceInstance(DataSource dataSource);
}
