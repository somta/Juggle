package net.somta.juggle.console.domain.datatype.repository;

import net.somta.juggle.console.domain.datatype.vo.DataTypeInfoVO;

import java.util.List;

/**
 * @author husong
 */
public interface IDataTypeInfoRepository {

    List<DataTypeInfoVO> queryDataTypeList();

}
