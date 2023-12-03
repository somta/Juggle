package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.api.ApiAO;
import net.somta.juggle.console.domain.api.vo.ApiVO;
import net.somta.juggle.console.domain.obj.ObjAO;
import net.somta.juggle.console.domain.obj.vo.PropertyVO;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.infrastructure.po.ApiPO;
import net.somta.juggle.console.infrastructure.po.ObjPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IObjConverter {
    IObjConverter IMPL = Mappers.getMapper(IObjConverter.class);


    ObjAO poToAo(ObjPO objPo);

    /*List<ApiVO> poListToVoList(List<ApiPO> apiList);*/

    default List<ParameterPO> propertyListToParameterList(Long sourceId, List<PropertyVO> propertyVoList){
        List<ParameterPO> propertyPoList = new ArrayList<>(propertyVoList.size());
        if(CollectionUtils.isNotEmpty(propertyVoList)){
            Date currentDate = new Date();
            ParameterPO parameterPo = null;
            for (PropertyVO propertyVo: propertyVoList) {
                parameterPo = new ParameterPO();
                parameterPo.setParamKey(propertyVo.getPropKey());
                parameterPo.setParamName(propertyVo.getPropName());
                parameterPo.setParamType(ParameterTypeEnum.PROPERTY.getCode());
                parameterPo.setDataType(propertyVo.getDataType());
                parameterPo.setSourceType(ParameterSourceTypeEnum.OBJ.getCode());
                parameterPo.setSourceId(sourceId);
                parameterPo.setCreatedAt(currentDate);
                propertyPoList.add(parameterPo);
            }
        }
        return propertyPoList;
    }

}
