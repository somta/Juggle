package net.somta.juggle.console.service.impl;

import net.somta.juggle.console.enums.DataTypeClassifyEnum;
import net.somta.juggle.console.mapper.DataTypeInfoMapper;
import net.somta.juggle.console.model.DataTypeInfo;
import net.somta.juggle.console.model.dto.DataTypeDTO;
import net.somta.juggle.console.model.dto.DataTypeOptionDTO;
import net.somta.juggle.console.service.IDataTypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataTypeInfoServiceImpl implements IDataTypeInfoService {

    @Autowired
    private DataTypeInfoMapper dataTypeInfoMapper;

    @Override
    public List<DataTypeOptionDTO> getDataTypeOptions() {
        List<DataTypeOptionDTO> list = new ArrayList<>();
        List<DataTypeInfo> dataTypeList = dataTypeInfoMapper.queryDataTypeList();
        Map<Integer, List<DataTypeInfo>> dataTypeMap = dataTypeList.stream().collect(Collectors.groupingBy(DataTypeInfo::getDataTypeClassify));
        for (Map.Entry<Integer, List<DataTypeInfo>> entry : dataTypeMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            DataTypeOptionDTO dataTypeOptionDTO = new DataTypeOptionDTO();
            dataTypeOptionDTO.setKey(DataTypeClassifyEnum.getKey(entry.getKey()));
            dataTypeOptionDTO.setName(DataTypeClassifyEnum.getName(entry.getKey()));
            dataTypeOptionDTO.setChildren(transformDataType(entry.getValue()));
            list.add(dataTypeOptionDTO);
        }
        return list;
    }


    /**
     * 将变量转成前端dto
     * @param list
     * @return
     */
    private List<DataTypeDTO> transformDataType(List<DataTypeInfo> list){
        List<DataTypeDTO> dataTypeDTOList = new ArrayList<>();
        for (DataTypeInfo dataTypeInfo : list ) {
            DataTypeDTO dataTypeDTO = new DataTypeDTO();
            dataTypeDTO.setType(dataTypeInfo.getDataType());
            dataTypeDTO.setDisplayName(dataTypeInfo.getDisplayName());
            dataTypeDTO.setObjectKey(dataTypeInfo.getObjectKey());
            dataTypeDTOList.add(dataTypeDTO);
        }
        return dataTypeDTOList;
    }

}
