package net.somta.juggle.console.application.service.system.impl;

import net.somta.juggle.console.application.assembler.system.IDataSourceAssembler;
import net.somta.juggle.console.application.service.system.IDataSourceManager;
import net.somta.juggle.console.configuration.JuggleProperties;
import net.somta.juggle.console.domain.system.datasource.DataSourceAO;
import net.somta.juggle.console.domain.system.datasource.repository.IDataSourceRepository;
import net.somta.juggle.console.domain.system.datasource.service.DataSourceInstanceFactory;
import net.somta.juggle.core.model.DataSource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DataSourceManager implements IDataSourceManager {

    private final IDataSourceRepository dataSourceRepository;
    private final JuggleProperties juggleProperties;
    private Map<String, Object> dataSourceCache;

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
        if (dataSourceCache.containsKey(dataSourceId)) {
            return dataSourceCache.get(dataSourceId);
        }
        DataSourceAO dataSourceAo = dataSourceRepository.queryDataSource(dataSourceId);
        dataSourceAo.decryptData(juggleProperties.getSecretKey());
        DataSource dataSource = IDataSourceAssembler.IMPL.aoToModel(dataSourceAo);
        Object dataSourceInstance = DataSourceInstanceFactory.getDataSourceInstance(dataSource);
        return dataSourceInstance;
    }
}
