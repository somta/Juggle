package net.somta.juggle.console.infrastructure.mapper;

import net.somta.juggle.console.infrastructure.po.DataTypeInfoPO;

import java.util.List;

public interface DataTypeInfoMapper {

    List<DataTypeInfoPO> queryDataTypeList();
}
