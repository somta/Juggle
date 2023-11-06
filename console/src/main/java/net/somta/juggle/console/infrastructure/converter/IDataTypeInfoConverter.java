package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.datatype.vo.DataTypeInfoVO;
import net.somta.juggle.console.infrastructure.po.DataTypeInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IDataTypeInfoConverter {
    IDataTypeInfoConverter IMPL = Mappers.getMapper(IDataTypeInfoConverter.class);

    List<DataTypeInfoVO> poListToVoList(List<DataTypeInfoPO> dataTypeInfoPoList);

}
