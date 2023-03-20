package net.somta.juggle.console.mapper;

import net.somta.juggle.console.model.DataTypeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface DataTypeInfoMapper {

    List<DataTypeInfo> queryDataTypeList();
}
