package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.datatype.repository.IDataTypeInfoRepository;
import net.somta.juggle.console.domain.datatype.vo.DataTypeInfoVO;
import net.somta.juggle.console.infrastructure.converter.IDataTypeInfoConverter;
import net.somta.juggle.console.infrastructure.mapper.DataTypeInfoMapper;
import net.somta.juggle.console.infrastructure.po.DataTypeInfoPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author husong
 */
@Repository
public class DataTypeInfoRepositoryImpl implements IDataTypeInfoRepository {

    private final DataTypeInfoMapper dataTypeInfoMapper;

    public DataTypeInfoRepositoryImpl(DataTypeInfoMapper dataTypeInfoMapper) {
        this.dataTypeInfoMapper = dataTypeInfoMapper;
    }

    @Override
    public List<DataTypeInfoVO> queryDataTypeList() {
        List<DataTypeInfoPO> dataTypeInfoPoList = dataTypeInfoMapper.queryDataTypeList();
        List<DataTypeInfoVO> dataTypeInfoVoList = IDataTypeInfoConverter.IMPL.poListToVoList(dataTypeInfoPoList);
        return dataTypeInfoVoList;
    }
}
