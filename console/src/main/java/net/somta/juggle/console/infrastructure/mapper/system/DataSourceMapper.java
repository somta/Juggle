package net.somta.juggle.console.infrastructure.mapper.system;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.system.DataSourcePO;

/**
 * @author husong
 * @since 1.2.0
 */
public interface DataSourceMapper extends IBaseMapper {
    Long addDataSource(DataSourcePO dataSourcePo);
}
