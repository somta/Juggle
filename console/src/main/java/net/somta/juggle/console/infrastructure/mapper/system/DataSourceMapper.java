package net.somta.juggle.console.infrastructure.mapper.system;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceQueryVO;
import net.somta.juggle.console.infrastructure.po.system.DataSourcePO;

import java.util.List;

/**
 * @author husong
 * @since 1.2.0
 */
public interface DataSourceMapper extends IBaseMapper {
    Long addDataSource(DataSourcePO dataSourcePo);

    List<DataSourcePO> queryDataSourceList(DataSourceQueryVO dataSourceQueryVO);
}
