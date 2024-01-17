package net.somta.juggle.console.infrastructure.mapper;

import net.somta.juggle.console.infrastructure.po.DataTypeInfoPO;

import java.util.List;

/**
 * @author husong
 */
public interface DataTypeInfoMapper {

    /**
     *
     * @return
     */
    List<DataTypeInfoPO> queryDataTypeList();
}
