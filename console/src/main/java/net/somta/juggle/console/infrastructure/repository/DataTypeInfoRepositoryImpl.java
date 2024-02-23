package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.datatype.DataTypeInfoEntity;
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
    public Boolean addDataTypeInfo(DataTypeInfoEntity dataTypeInfoEntity) {
        dataTypeInfoMapper.add(IDataTypeInfoConverter.IMPL.entityToPo(dataTypeInfoEntity));
        return true;
    }

    @Override
    public Boolean deleteObjectDataTypeInfoByKey(String objectKey) {
        dataTypeInfoMapper.deleteObjectDataTypeInfoByKey(objectKey);
        return true;
    }

    @Override
    public Boolean updateDataTypeInfo(DataTypeInfoEntity dataTypeInfoEntity) {
        dataTypeInfoMapper.update(IDataTypeInfoConverter.IMPL.entityToPo(dataTypeInfoEntity));
        return true;
    }

    @Override
    public DataTypeInfoEntity queryObjectDataTypeInfoByKey(String objectKey) {
        DataTypeInfoPO dataTypeInfoPo = dataTypeInfoMapper.queryObjectDataTypeInfoByKey(objectKey);
        DataTypeInfoEntity dataTypeInfoEntity = IDataTypeInfoConverter.IMPL.poToEntity(dataTypeInfoPo);
        return dataTypeInfoEntity;
    }

    @Override
    public List<DataTypeInfoVO> queryDataTypeList() {
        List<DataTypeInfoPO> dataTypeInfoPoList = dataTypeInfoMapper.queryDataTypeList();
        List<DataTypeInfoVO> dataTypeInfoVoList = IDataTypeInfoConverter.IMPL.poListToVoList(dataTypeInfoPoList);
        return dataTypeInfoVoList;
    }
}
