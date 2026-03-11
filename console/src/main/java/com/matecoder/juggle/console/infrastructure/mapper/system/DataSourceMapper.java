package com.matecoder.juggle.console.infrastructure.mapper.system;

import com.matecoder.core.base.IBaseMapper;
import com.matecoder.juggle.console.domain.system.datasource.vo.DataSourceQueryVO;
import com.matecoder.juggle.console.infrastructure.po.system.DataSourcePO;

import java.util.List;

/**
 * @author husong
 * @since 1.2.0
 */
public interface DataSourceMapper extends IBaseMapper {
    Long addDataSource(DataSourcePO dataSourcePo);

    List<DataSourcePO> queryDataSourceList(DataSourceQueryVO dataSourceQueryVO);
}
