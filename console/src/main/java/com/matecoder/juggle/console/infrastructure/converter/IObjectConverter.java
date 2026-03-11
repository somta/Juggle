package com.matecoder.juggle.console.infrastructure.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.matecoder.common.utils.JsonUtil;
import com.matecoder.juggle.console.domain.object.ObjectAO;
import com.matecoder.juggle.console.domain.object.vo.ObjectVO;
import com.matecoder.juggle.console.domain.object.vo.PropertyVO;
import com.matecoder.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import com.matecoder.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import com.matecoder.juggle.console.infrastructure.po.ObjectPO;
import com.matecoder.juggle.console.infrastructure.po.ParameterPO;
import com.matecoder.juggle.console.infrastructure.view.ObjectInfoView;
import com.matecoder.juggle.core.model.DataType;
import com.matecoder.juggle.core.model.Property;
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

    default List<ObjectVO> viewListToVoList(List<ObjectInfoView> objectViewList){
        if(objectViewList == null){
            return null;
        }
        List<ObjectVO> list = new ArrayList<>(objectViewList.size());
        ObjectVO objectVo = null;
        for (ObjectInfoView objectInfoView : objectViewList){
            objectVo = new ObjectVO();
            objectVo.setId(objectInfoView.getId());
            objectVo.setObjectKey(objectInfoView.getObjectKey());
            objectVo.setObjectName(objectInfoView.getObjectName());
            objectVo.setObjectDesc(objectInfoView.getObjectDesc());
            List<PropertyVO> propertyVoList = objectInfoView.getPropertyList();
            if(CollectionUtils.isNotEmpty(propertyVoList)){
                List<Property> propertyList = new ArrayList<>();
                Property property = null;
                for (PropertyVO propertyVo : propertyVoList){
                    property = new Property();
                    property.setPropKey(propertyVo.getPropKey());
                    property.setPropName(propertyVo.getPropName());
                    try {
                        property.setDataType(JsonUtil.deserialize(propertyVo.getDataType(), DataType.class));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    propertyList.add(property);
                }
                objectVo.setPropertyList(propertyList);
            }
            list.add(objectVo);
        }
        return list;
    }

    default List<ParameterPO> propertyListToParameterList(Long sourceId, List<Property> propertyList){
        List<ParameterPO> propertyPoList = new ArrayList<>(propertyList.size());
        if(CollectionUtils.isNotEmpty(propertyList)){
            Date currentDate = new Date();
            ParameterPO parameterPo = null;
            for (Property property: propertyList) {
                parameterPo = new ParameterPO();
                parameterPo.setParamKey(property.getPropKey());
                parameterPo.setParamName(property.getPropName());
                parameterPo.setParamType(ParameterTypeEnum.PROPERTY.getCode());
                try {
                    parameterPo.setDataType(JsonUtil.serialize(property.getDataType()));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                parameterPo.setSourceType(ParameterSourceTypeEnum.OBJECT.getCode());
                parameterPo.setSourceId(sourceId);
                parameterPo.setCreatedAt(currentDate);
                propertyPoList.add(parameterPo);
            }
        }
        return propertyPoList;
    }

}
