package net.somta.juggle.console.infrastructure.mapper;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.DataTypeInfoPO;

import java.util.List;

/**
 * @author husong
 */
public interface DataTypeInfoMapper extends IBaseMapper {

    /**
     *
     * @return
     */
    List<DataTypeInfoPO> queryDataTypeList();



}
