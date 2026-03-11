package com.matecoder.juggle.console.application.assembler;

import com.matecoder.juggle.console.domain.object.vo.ObjectVO;
import com.matecoder.juggle.console.interfaces.dto.DataTypeOptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IDataTypeInfoAssembler {
    IDataTypeInfoAssembler IMPL = Mappers.getMapper(IDataTypeInfoAssembler.class);

    default List<DataTypeOptionDTO> objectListToDataTypeOptionDtoList(List<ObjectVO> objectVoList){
        if (objectVoList == null ) {
            return null;
        }
        List<DataTypeOptionDTO> list = new ArrayList<>(objectVoList.size());
        DataTypeOptionDTO dataTypeOptionDTO = null;
        for ( ObjectVO objectVo : objectVoList ) {
            dataTypeOptionDTO = new DataTypeOptionDTO();
            dataTypeOptionDTO.setId(objectVo.getId());
            dataTypeOptionDTO.setDataTypeClassify(3);
            dataTypeOptionDTO.setType("Object");
            dataTypeOptionDTO.setObjectKey(objectVo.getObjectKey());
            dataTypeOptionDTO.setDisplayName(objectVo.getObjectName());
            dataTypeOptionDTO.setObjectStructure(objectVo.getPropertyList());
            list.add(dataTypeOptionDTO);
        }
        return list;
    }
}
