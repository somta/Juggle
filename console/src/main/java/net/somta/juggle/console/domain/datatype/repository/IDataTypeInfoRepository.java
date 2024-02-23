package net.somta.juggle.console.domain.datatype.repository;

import net.somta.juggle.console.domain.datatype.DataTypeInfoEntity;
import net.somta.juggle.console.domain.datatype.vo.DataTypeInfoVO;
import net.somta.juggle.console.infrastructure.po.DataTypeInfoPO;

import java.util.List;

/**
 * @author husong
 */
public interface IDataTypeInfoRepository {

    Boolean addDataTypeInfo(DataTypeInfoEntity dataTypeInfoEntity);

    Boolean deleteObjectDataTypeInfoByKey(String objectKey);

    Boolean updateDataTypeInfo(DataTypeInfoEntity dataTypeInfoEntity);

    DataTypeInfoEntity queryObjectDataTypeInfoByKey(String objectKey);

    List<DataTypeInfoVO> queryDataTypeList();



}
