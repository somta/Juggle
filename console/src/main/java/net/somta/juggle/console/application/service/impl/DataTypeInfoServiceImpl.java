package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.domain.datatype.enums.DataTypeClassifyEnum;
import net.somta.juggle.console.infrastructure.mapper.DataTypeInfoMapper;
import net.somta.juggle.console.infrastructure.po.DataTypeInfoPO;
import net.somta.juggle.console.interfaces.dto.DataTypeDTO;
import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;
import net.somta.juggle.console.application.service.IDataTypeInfoService;
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
        List<DataTypeInfoPO> dataTypeList = dataTypeInfoMapper.queryDataTypeList();
        Map<Integer, List<DataTypeInfoPO>> dataTypeMap = dataTypeList.stream().collect(Collectors.groupingBy(DataTypeInfoPO::getDataTypeClassify));
        for (Map.Entry<Integer, List<DataTypeInfoPO>> entry : dataTypeMap.entrySet()) {
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
    private List<DataTypeDTO> transformDataType(List<DataTypeInfoPO> list){
        List<DataTypeDTO> dataTypeDTOList = new ArrayList<>();
        for (DataTypeInfoPO dataTypeInfoPO : list ) {
            DataTypeDTO dataTypeDTO = new DataTypeDTO();
            dataTypeDTO.setType(dataTypeInfoPO.getDataType());
            dataTypeDTO.setDisplayName(dataTypeInfoPO.getDisplayName());
            dataTypeDTO.setObjectKey(dataTypeInfoPO.getObjectKey());
            dataTypeDTOList.add(dataTypeDTO);
        }
        return dataTypeDTOList;
    }

}
