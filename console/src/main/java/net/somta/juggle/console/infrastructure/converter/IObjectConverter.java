package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.object.ObjectAO;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.domain.object.vo.PropertyVO;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.infrastructure.po.ObjectPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.infrastructure.view.ObjectInfoView;
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
public interface IObjectConverter {
    IObjectConverter IMPL = Mappers.getMapper(IObjectConverter.class);


    ObjectAO poToAo(ObjectPO objectPo);

    List<ObjectVO> poListToVoList(List<ObjectPO> objectPoList);

    List<ObjectVO> viewListToVoList(List<ObjectInfoView> objectViewList);

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
                parameterPo.setSourceType(ParameterSourceTypeEnum.OBJECT.getCode());
                parameterPo.setSourceId(sourceId);
                parameterPo.setCreatedAt(currentDate);
                propertyPoList.add(parameterPo);
            }
        }
        return propertyPoList;
    }

}
