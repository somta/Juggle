package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.application.assembler.IDataTypeInfoAssembler;
import net.somta.juggle.console.domain.object.repository.IObjectRepository;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;
import net.somta.juggle.console.application.service.IDataTypeInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author husong
 */
@Service
public class DataTypeInfoServiceImpl implements IDataTypeInfoService {

    private final IObjectRepository objectRepository;

    public DataTypeInfoServiceImpl(IObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    @Override
    public List<DataTypeOptionDTO> getDataTypeOptions() {
        List<DataTypeOptionDTO> dataTypeOptions = getDefaultDataType();
        List<ObjectVO> objectVoList = objectRepository.queryObjectInfoList();
        List<DataTypeOptionDTO> objectDataTypeList =IDataTypeInfoAssembler.IMPL.objectListToDataTypeOptionDtoList(objectVoList);
        if(CollectionUtils.isNotEmpty(objectDataTypeList)){
            dataTypeOptions.addAll(objectDataTypeList);
        }
        return dataTypeOptions;
    }

    private List<DataTypeOptionDTO> getDefaultDataType(){
        List<DataTypeOptionDTO> defaultDataType = new ArrayList<>();
        defaultDataType.add(new DataTypeOptionDTO(-1L,1,"String","字符串"));
        defaultDataType.add(new DataTypeOptionDTO(-2L,1,"Integer","整数"));
        defaultDataType.add(new DataTypeOptionDTO(-3L,1,"Double","小数"));
        defaultDataType.add(new DataTypeOptionDTO(-4L,1,"Boolean","布尔"));
        defaultDataType.add(new DataTypeOptionDTO(-5L,1,"Date","时间"));
        defaultDataType.add(new DataTypeOptionDTO(-10L,2,"List","集合"));
        return defaultDataType;
    }


}
