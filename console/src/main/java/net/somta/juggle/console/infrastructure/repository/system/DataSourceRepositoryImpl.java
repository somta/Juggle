package net.somta.juggle.console.infrastructure.repository.system;

import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.system.datasource.DataSourceAO;
import net.somta.juggle.console.domain.system.datasource.repository.IDataSourceRepository;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceQueryVO;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceVO;
import net.somta.juggle.console.infrastructure.converter.system.IDataSourceConverter;
import net.somta.juggle.console.infrastructure.mapper.system.DataSourceMapper;
import net.somta.juggle.console.infrastructure.po.system.DataSourcePO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class DataSourceRepositoryImpl implements IDataSourceRepository {
    private final DataSourceMapper dataSourceMapper;

    public DataSourceRepositoryImpl(DataSourceMapper dataSourceMapper) {
        this.dataSourceMapper = dataSourceMapper;
    }

    @Override
    public Long addDataSource(DataSourceAO dataSourceAo) {
        DataSourcePO dataSourcePo = IDataSourceConverter.IMPL.aoToPo(dataSourceAo);
        dataSourcePo.setCreatedAt(new Date());
        dataSourcePo.setCreatedBy(IdentityContext.getIdentity().getUserId());
        Long dataSourceId = dataSourceMapper.addDataSource(dataSourcePo);
        return dataSourceId;
    }

    @Override
    public void deleteDataSourceById(Long dataSourceId) {
        dataSourceMapper.deleteById(dataSourceId);
    }

    @Override
    public Boolean updateDataSource(DataSourceAO dataSourceAo) {
        DataSourcePO dataSourcePo = IDataSourceConverter.IMPL.aoToPo(dataSourceAo);
        dataSourcePo.setUpdatedAt(new Date());
        dataSourcePo.setUpdatedBy(IdentityContext.getIdentity().getUserId());
        dataSourceMapper.update(dataSourcePo);
        return true;
    }

    @Override
    public DataSourceAO queryDataSource(Long dataSourceId) {
        DataSourcePO dataSourcePo = dataSourceMapper.queryById(dataSourceId);
        return IDataSourceConverter.IMPL.poToAo(dataSourcePo);
    }

    @Override
    public List<DataSourceVO> queryDataSourcePageList(DataSourceQueryVO dataSourceQueryVO) {
        List<DataSourcePO> dataSourceList =dataSourceMapper.queryByList(dataSourceQueryVO);
        return IDataSourceConverter.IMPL.poListToVoList(dataSourceList);
    }


}
