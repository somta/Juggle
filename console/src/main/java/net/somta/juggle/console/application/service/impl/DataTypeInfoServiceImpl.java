package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.application.assembler.IDataTypeInfoAssembler;
import net.somta.juggle.console.domain.datatype.repository.IDataTypeInfoRepository;
import net.somta.juggle.console.domain.datatype.vo.DataTypeInfoVO;
import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;
import net.somta.juggle.console.application.service.IDataTypeInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<DataTypeOptionDTO> getDataTypeOptions(Integer dataTypeClassify) {
        List<DataTypeInfoVO> dataTypeList = dataTypeInfoRepository.queryDataTypeList(dataTypeClassify);
        List<DataTypeOptionDTO> dataTypeOptions = IDataTypeInfoAssembler.IMPL.voListToDtoList(dataTypeList);
        return dataTypeOptions;
    }


}
