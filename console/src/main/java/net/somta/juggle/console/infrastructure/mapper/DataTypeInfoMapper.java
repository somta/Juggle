package net.somta.juggle.console.infrastructure.mapper;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.datatype.vo.DataTypeInfoVO;
import net.somta.juggle.console.infrastructure.po.DataTypeInfoPO;

import java.util.List;

/**
 * @author husong
 */
public interface DataTypeInfoMapper extends IBaseMapper {

    int deleteObjectDataTypeInfoByKey(String objectKey);

    DataTypeInfoPO queryObjectDataTypeInfoByKey(String objectKey);

    /**
     *
     * @return
     */
    List<DataTypeInfoPO> queryDataTypeList();



}
