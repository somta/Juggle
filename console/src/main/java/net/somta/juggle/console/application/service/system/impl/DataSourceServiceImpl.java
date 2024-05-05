package net.somta.juggle.console.application.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.assembler.system.IDataSourceAssembler;
import net.somta.juggle.console.application.service.system.IDataSourceManager;
import net.somta.juggle.console.application.service.system.IDataSourceService;
import net.somta.juggle.console.domain.system.datasource.DataSourceAO;
import net.somta.juggle.console.domain.system.datasource.repository.IDataSourceRepository;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceVO;
import net.somta.juggle.console.interfaces.dto.system.DataSourceDTO;
import net.somta.juggle.console.interfaces.param.system.DataSourceAddParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceQueryParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceUpdateParam;
import net.somta.juggle.core.executor.data.IDataSource;
import net.somta.juggle.core.model.DataSource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 * @since 1.2.0
 */
@Service
public class DataSourceServiceImpl implements IDataSourceService {
    private final IDataSourceRepository dataSourceRepository;
    private final IDataSourceManager dataSourceManager;

    public DataSourceServiceImpl(IDataSourceRepository dataSourceRepository, IDataSourceManager dataSourceManager) {
        this.dataSourceRepository = dataSourceRepository;
        this.dataSourceManager = dataSourceManager;
    }

    @Override
    public Boolean addDataSource(DataSourceAddParam dataSourceAddParam) {
        DataSourceAO dataSourceAo = IDataSourceAssembler.IMPL.paramToAo(dataSourceAddParam);
        Long dataSourceId = dataSourceRepository.addDataSource(dataSourceAo);
        DataSource dataSource = IDataSourceAssembler.IMPL.aoToModel(dataSourceAo);
        dataSource.setId(dataSourceId);
        dataSourceManager.addDataSourceToCache(dataSource);
        return true;
    }

    @Override
    public void deleteDataSource(Long dataSourceId) {
        dataSourceRepository.deleteDataSourceById(dataSourceId);
        dataSourceManager.deleteDataSourceFromCache(dataSourceId);
    }

    @Override
    public Boolean updateDataSource(DataSourceUpdateParam dataSourceUpdateParam) {
        DataSourceAO dataSourceAo = IDataSourceAssembler.IMPL.paramToAo(dataSourceUpdateParam);
        dataSourceManager.deleteDataSourceFromCache(dataSourceUpdateParam.getId());
        return dataSourceRepository.updateDataSource(dataSourceAo);
    }

    @Override
    public DataSourceDTO getDataSource(Long dataSourceId) {
        DataSourceAO dataSourceAo = dataSourceRepository.queryDataSource(dataSourceId);
        return IDataSourceAssembler.IMPL.aoToDto(dataSourceAo);
    }

    @Override
    public PageInfo getDataSourcePageList(DataSourceQueryParam dataSourceQueryParam) {
        Page<DataSourceDTO> page = PageHelper.startPage(dataSourceQueryParam.getPageNum(), dataSourceQueryParam.getPageSize());
        List<DataSourceVO> dataSourceVoList = dataSourceRepository.queryDataSourcePageList(IDataSourceAssembler.IMPL.paramToVo(dataSourceQueryParam));
        List<DataSourceDTO> dataSourceList = IDataSourceAssembler.IMPL.voListToDtoList(dataSourceVoList);
        PageInfo pageInfo = new PageInfo(dataSourceList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
}
