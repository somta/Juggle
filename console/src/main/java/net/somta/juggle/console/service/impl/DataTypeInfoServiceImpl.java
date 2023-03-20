package net.somta.juggle.console.service.impl;

import net.somta.juggle.console.mapper.DataTypeInfoMapper;
import net.somta.juggle.console.model.DataTypeInfo;
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
            dataTypeOptionDTO.setLable(entry.getKey().toString());
            dataTypeOptionDTO.setChilds(entry.getValue());
            list.add(dataTypeOptionDTO);
        }
        return list;
    }
}
