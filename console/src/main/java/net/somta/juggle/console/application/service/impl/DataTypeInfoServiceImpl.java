package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.application.assembler.IDataTypeInfoAssembler;
import net.somta.juggle.console.domain.datatype.repository.IDataTypeInfoRepository;
import net.somta.juggle.console.domain.datatype.vo.DataTypeInfoVO;
import net.somta.juggle.console.domain.object.repository.IObjectRepository;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;
import net.somta.juggle.console.application.service.IDataTypeInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 */
@Service
public class DataTypeInfoServiceImpl implements IDataTypeInfoService {

    private final IDataTypeInfoRepository dataTypeInfoRepository;
    private final IObjectRepository objectRepository;

    public DataTypeInfoServiceImpl(IDataTypeInfoRepository dataTypeInfoRepository, IObjectRepository objectRepository) {
        this.dataTypeInfoRepository = dataTypeInfoRepository;
        this.objectRepository = objectRepository;
    }

    @Override
    public List<DataTypeOptionDTO> getDataTypeOptions() {
        List<DataTypeInfoVO> dataTypeList = dataTypeInfoRepository.queryDataTypeList();
        List<ObjectVO> objectVoList = objectRepository.queryObjectList();
        List<DataTypeInfoVO> objectDataTypeList =IDataTypeInfoAssembler.IMPL.objectListToDataTypeInfoVoList(objectVoList);
        if(CollectionUtils.isNotEmpty(objectDataTypeList)){
            dataTypeList.addAll(objectDataTypeList);
        }
        List<DataTypeOptionDTO> dataTypeOptions = IDataTypeInfoAssembler.IMPL.voListToDtoList(dataTypeList);
        return dataTypeOptions;
    }


}
