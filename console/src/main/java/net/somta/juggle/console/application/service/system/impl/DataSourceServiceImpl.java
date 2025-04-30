package net.somta.juggle.console.application.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.common.encrypt.AESUtil;
import net.somta.juggle.console.application.assembler.system.IDataSourceAssembler;
import net.somta.juggle.console.application.service.system.IDataSourceManager;
import net.somta.juggle.console.application.service.system.IDataSourceService;
import net.somta.juggle.console.configuration.JuggleProperties;
import net.somta.juggle.console.domain.system.datasource.DataSourceAO;
import net.somta.juggle.console.domain.system.datasource.repository.IDataSourceRepository;
import net.somta.juggle.console.domain.system.datasource.service.DataSourceInstanceFactory;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceQueryVO;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceVO;
import net.somta.juggle.console.interfaces.dto.system.DataSourceDTO;
import net.somta.juggle.console.interfaces.param.system.DataSourceAddParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceQueryParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceUpdateParam;
import net.somta.juggle.core.model.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;

/**
 * @author husong
 * @since 1.2.0
 */
@Service
public class DataSourceServiceImpl implements IDataSourceService {
    private final IDataSourceRepository dataSourceRepository;
    private final IDataSourceManager dataSourceManager;
    private final JuggleProperties juggleProperties;

    public DataSourceServiceImpl(IDataSourceRepository dataSourceRepository, IDataSourceManager dataSourceManager, JuggleProperties juggleProperties) {
        this.dataSourceRepository = dataSourceRepository;
        this.dataSourceManager = dataSourceManager;
        this.juggleProperties = juggleProperties;
    }

    @Override
    public Boolean addDataSource(DataSourceAddParam dataSourceAddParam) {
        DataSourceAO dataSourceAo = IDataSourceAssembler.IMPL.paramToAo(dataSourceAddParam);
        Long dataSourceId = dataSourceRepository.addDataSource(dataSourceAo);
        DataSource dataSource = IDataSourceAssembler.IMPL.aoToModel(dataSourceAo);
        dataSource.setId(dataSourceId);
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
    public List<DataSourceDTO> getAllDataSourceList() {
        List<DataSourceVO> dataSourceVoList = dataSourceRepository.queryDataSourceList(new DataSourceQueryVO());
        List<DataSourceDTO> dataSourceList = IDataSourceAssembler.IMPL.voListToDtoList(dataSourceVoList);
        return dataSourceList;
    }

    @Override
    public PageInfo getDataSourcePageList(DataSourceQueryParam dataSourceQueryParam) {
        Page<DataSourceDTO> page = PageHelper.startPage(dataSourceQueryParam.getPageNum(), dataSourceQueryParam.getPageSize());
        List<DataSourceVO> dataSourceVoList = dataSourceRepository.queryDataSourceList(IDataSourceAssembler.IMPL.paramToVo(dataSourceQueryParam));
        List<DataSourceDTO> dataSourceList = IDataSourceAssembler.IMPL.voListToDtoList(dataSourceVoList);
        PageInfo pageInfo = new PageInfo(dataSourceList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public Boolean connectDataSource(Long dataSourceId) {
        DataSourceAO dataSourceAo = dataSourceRepository.queryDataSource(dataSourceId);
        DataSource dataSource = IDataSourceAssembler.IMPL.aoToModel(dataSourceAo);
        dataSource.setId(dataSourceId);
        Object dataSourceInstance = DataSourceInstanceFactory.getDataSourceInstance(dataSource);
        if(dataSourceInstance != null){
            return true;
        }
        return false;
    }
}
