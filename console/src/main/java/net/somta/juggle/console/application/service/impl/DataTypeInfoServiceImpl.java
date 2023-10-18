package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.domain.datatype.enums.DataTypeClassifyEnum;
import net.somta.juggle.console.domain.datatype.repository.IDataTypeInfoRepository;
import net.somta.juggle.console.domain.datatype.vo.DataTypeInfoVO;
import net.somta.juggle.console.infrastructure.po.DataTypeInfoPO;
import net.somta.juggle.console.interfaces.dto.DataTypeDTO;
import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;
import net.somta.juggle.console.application.service.IDataTypeInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author husong
 */
@Service
public class DataTypeInfoServiceImpl implements IDataTypeInfoService {

    private final IDataTypeInfoRepository dataTypeInfoRepository;

    public DataTypeInfoServiceImpl(IDataTypeInfoRepository dataTypeInfoRepository) {
        this.dataTypeInfoRepository = dataTypeInfoRepository;
    }

    @Override
    public List<DataTypeOptionDTO> getDataTypeOptions() {
        List<DataTypeOptionDTO> list = new ArrayList<>();
        List<DataTypeInfoVO> dataTypeList = dataTypeInfoRepository.queryDataTypeList();
        Map<Integer, List<DataTypeInfoVO>> dataTypeMap = dataTypeList.stream().collect(Collectors.groupingBy(DataTypeInfoVO::getDataTypeClassify));
        for (Map.Entry<Integer, List<DataTypeInfoVO>> entry : dataTypeMap.entrySet()) {
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
    private List<DataTypeDTO> transformDataType(List<DataTypeInfoVO> list){
        List<DataTypeDTO> dataTypeDTOList = new ArrayList<>();
        for (DataTypeInfoVO dataTypeInfo : list ) {
            DataTypeDTO dataTypeDTO = new DataTypeDTO();
            dataTypeDTO.setType(dataTypeInfo.getDataType());
            dataTypeDTO.setDisplayName(dataTypeInfo.getDisplayName());
            dataTypeDTO.setObjectKey(dataTypeInfo.getObjectKey());
            dataTypeDTOList.add(dataTypeDTO);
        }
        return dataTypeDTOList;
    }

}
