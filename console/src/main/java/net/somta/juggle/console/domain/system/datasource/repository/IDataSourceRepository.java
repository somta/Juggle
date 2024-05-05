package net.somta.juggle.console.domain.system.datasource.repository;

import net.somta.juggle.console.domain.system.datasource.DataSourceAO;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceQueryVO;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceVO;

import java.util.List;

/**
 * @author husong
 * @since 1.2.0
 */
public interface IDataSourceRepository {
    Long addDataSource(DataSourceAO dataSourceAo);

    void deleteDataSourceById(Long dataSourceId);

    Boolean updateDataSource(DataSourceAO dataSourceAo);

    DataSourceAO queryDataSource(Long dataSourceId);

    List<DataSourceVO> queryDataSourcePageList(DataSourceQueryVO dataSourceQueryVO);
}
