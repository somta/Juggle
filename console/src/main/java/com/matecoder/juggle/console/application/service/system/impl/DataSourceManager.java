package com.matecoder.juggle.console.application.service.system.impl;

import com.matecoder.juggle.console.application.assembler.system.IDataSourceAssembler;
import com.matecoder.juggle.console.application.service.system.IDataSourceManager;
import com.matecoder.juggle.console.configuration.JuggleProperties;
import com.matecoder.juggle.console.domain.system.datasource.DataSourceAO;
import com.matecoder.juggle.console.domain.system.datasource.repository.IDataSourceRepository;
import com.matecoder.juggle.console.domain.system.datasource.service.DataSourceInstanceFactory;
import com.matecoder.juggle.core.model.DataSource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DataSourceManager implements IDataSourceManager {

    private final IDataSourceRepository dataSourceRepository;
    private final JuggleProperties juggleProperties;
    private final Map<Long, Object> dataSourceCache;

    public DataSourceManager(IDataSourceRepository dataSourceRepository, JuggleProperties juggleProperties) {
        this.juggleProperties = juggleProperties;
        this.dataSourceRepository = dataSourceRepository;
        dataSourceCache = new ConcurrentHashMap<>();
    }

    @Override
    public void deleteDataSourceFromCache(Long dataSourceId) {
        dataSourceCache.remove(dataSourceId);
    }

    @Override
    public Object getDataSource(Long dataSourceId) {
        Object cacheDataSource = dataSourceCache.get(dataSourceId);
        if (cacheDataSource != null) {
            return cacheDataSource;
        }
        DataSourceAO dataSourceAo = dataSourceRepository.queryDataSource(dataSourceId);
        DataSource dataSource = IDataSourceAssembler.IMPL.aoToModel(dataSourceAo);
        Object dataSourceInstance = DataSourceInstanceFactory.getDataSourceInstance(dataSource);
        if(dataSourceInstance != null){
            dataSourceCache.put(dataSourceId, dataSourceInstance);
        }
        return dataSourceInstance;
    }
}
