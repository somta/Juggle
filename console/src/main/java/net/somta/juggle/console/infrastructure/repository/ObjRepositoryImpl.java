package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.obj.ObjAO;
import net.somta.juggle.console.domain.obj.repository.IObjRepository;
import net.somta.juggle.console.domain.obj.vo.ObjVO;
import net.somta.juggle.console.domain.obj.vo.PropertyVO;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.infrastructure.converter.IObjConverter;
import net.somta.juggle.console.infrastructure.mapper.ObjMapper;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.po.ObjPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.interfaces.param.ObjQueryParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author husong
 */
@Repository
public class ObjRepositoryImpl implements IObjRepository {
    private final ObjMapper objMapper;
    private final ParameterMapper parameterMapper;

    public ObjRepositoryImpl(ObjMapper objMapper, ParameterMapper parameterMapper) {
        this.objMapper = objMapper;
        this.parameterMapper = parameterMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addObj(ObjAO objAO) {
        ObjPO objPo = new ObjPO();
        objPo.setObjCode(objAO.getObjCode());
        objPo.setObjName(objAO.getObjName());
        objPo.setObjDesc(objAO.getObjDesc());
        objPo.setCreatedAt(new Date());
        objMapper.addObj(objPo);

        List<ParameterPO> propertyPoList = IObjConverter.IMPL.propertyListToParameterList(objPo.getId(),objAO.getPropertyList());
        if(CollectionUtils.isNotEmpty(propertyPoList)){
            parameterMapper.batchAddParameter(propertyPoList);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteObjById(Long objId) {
        objMapper.deleteById(objId);
        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.OBJ.getCode(),objId));
        return true;
    }

    @Override
    public Boolean updateObj(ObjAO objAO) {
        ObjPO objPo = new ObjPO();
        objPo.setId(objAO.getId());
        objPo.setObjCode(objAO.getObjCode());
        objPo.setObjName(objAO.getObjName());
        objPo.setObjDesc(objAO.getObjDesc());
        objMapper.update(objPo);

        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.OBJ.getCode(),objAO.getId()));
        List<ParameterPO> propertyPoList = IObjConverter.IMPL.propertyListToParameterList(objPo.getId(),objAO.getPropertyList());
        if(CollectionUtils.isNotEmpty(propertyPoList)){
            parameterMapper.batchAddParameter(propertyPoList);
        }
        return true;
    }

    @Override
    public ObjAO queryApi(Long objId) {
        ObjPO objPo =objMapper.queryById(objId);
        ObjAO objAo = IObjConverter.IMPL.poToAo(objPo);
        List<ParameterPO> propertyList = parameterMapper.getParameterListByVO(new ParameterVO(ParameterSourceTypeEnum.OBJ.getCode(),objId));
        objAo.parseProperty(propertyList);
        return objAo;
    }

    @Override
    public List<ObjVO> queryObjPageList(ObjQueryParam objQueryParam) {
        return null;
    }
}
