package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.datatype.vo.DataTypeInfoVO;
import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IDataTypeInfoAssembler {
    IDataTypeInfoAssembler IMPL = Mappers.getMapper(IDataTypeInfoAssembler.class);
    @Mapping(target = "type", source = "dataType")
    DataTypeOptionDTO voToDto(DataTypeInfoVO dataTypeInfoVo);

    List<DataTypeOptionDTO> voListToDtoList(List<DataTypeInfoVO> dataTypeList);
}
