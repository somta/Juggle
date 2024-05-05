package net.somta.juggle.console.application.service.system.impl;

import net.somta.juggle.console.application.assembler.system.IDataSourceAssembler;
import net.somta.juggle.console.application.service.system.IDataSourceManager;
import net.somta.juggle.console.domain.system.datasource.DataSourceAO;
import net.somta.juggle.console.domain.system.datasource.repository.IDataSourceRepository;
import net.somta.juggle.core.executor.data.IDataSource;
import net.somta.juggle.core.model.DataSource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DataSourceManager implements IDataSourceManager {

    private final IDataSourceRepository dataSourceRepository;
    private Map<String, Object> dataSourceCache;

    public DataSourceManager(IDataSourceRepository dataSourceRepository) {
        dataSourceCache = new ConcurrentHashMap<>();
        this.dataSourceRepository = dataSourceRepository;
    }

    @Override
    public void addDataSourceToCache(DataSource dataSource) {
        //todo 转换成对应的数据源对象
        //dataSourceCache.put(dataSource.getId().toString(), dataSource);
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
        DataSource dataSource = IDataSourceAssembler.IMPL.aoToModel(dataSourceAo);
        //todo 转换成对应的数据源对象
        return dataSource;
    }
}
