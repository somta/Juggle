package net.somta.juggle.console.infrastructure.mapper;

import net.somta.juggle.console.infrastructure.model.DataTypeInfo;

import java.util.List;

public interface DataTypeInfoMapper {

    List<DataTypeInfo> queryDataTypeList();
}
