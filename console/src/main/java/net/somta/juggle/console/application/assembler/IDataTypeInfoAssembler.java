package net.somta.juggle.console.application.assembler;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.datatype.vo.DataTypeInfoVO;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
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

    default List<DataTypeInfoVO> objectListToDataTypeInfoVoList(List<ObjectVO> objectVoList){
        if (objectVoList == null ) {
            return null;
        }
        List<DataTypeInfoVO> list = new ArrayList<>(objectVoList.size());
        for ( ObjectVO objectVo : objectVoList ) {
            DataTypeInfoVO dataTypeInfo = new DataTypeInfoVO();
            dataTypeInfo.setId(objectVo.getId());
            dataTypeInfo.setDataTypeClassify(3);
            dataTypeInfo.setDataType("Object");
            dataTypeInfo.setObjectKey(objectVo.getObjectCode());
            dataTypeInfo.setDisplayName(objectVo.getObjectName());
            dataTypeInfo.setObjectStructure(JsonSerializeHelper.serialize(objectVo.getPropertyList()));
            list.add(dataTypeInfo);
        }
        return list;
    }
}
