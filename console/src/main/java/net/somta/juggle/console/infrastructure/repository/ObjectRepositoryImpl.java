/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.object.ObjectAO;
import net.somta.juggle.console.domain.object.repository.IObjectRepository;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.infrastructure.converter.IObjectConverter;
import net.somta.juggle.console.infrastructure.mapper.ObjectMapper;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.po.ObjectPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.infrastructure.view.ObjectInfoView;
import net.somta.juggle.console.interfaces.param.ObjectQueryParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author husong
 * @since 1.0.0
 */
@Repository
public class ObjectRepositoryImpl implements IObjectRepository {
    private final ObjectMapper objectMapper;
    private final ParameterMapper parameterMapper;

    public ObjectRepositoryImpl(ObjectMapper objectMapper, ParameterMapper parameterMapper) {
        this.objectMapper = objectMapper;
        this.parameterMapper = parameterMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addObject(ObjectAO objectAo) {
        ObjectPO objectPo = new ObjectPO();
        objectPo.setObjectKey(objectAo.getObjectKey());
        objectPo.setObjectName(objectAo.getObjectName());
        objectPo.setObjectDesc(objectAo.getObjectDesc());
        objectPo.setCreatedAt(new Date());
        objectPo.setCreatedBy(IdentityContext.getIdentity().getUserId());
        objectMapper.addObject(objectPo);

        List<ParameterPO> propertyPoList = IObjectConverter.IMPL.propertyListToParameterList(objectPo.getId(), objectAo.getPropertyList());
        if(CollectionUtils.isNotEmpty(propertyPoList)){
            parameterMapper.batchAddParameter(propertyPoList);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteObjectById(Long objId) {
        objectMapper.deleteById(objId);
        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.OBJECT.getCode(),objId));
        return true;
    }

    @Override
    public Boolean updateObject(ObjectAO objectAo) {
        ObjectPO objectPo = new ObjectPO();
        objectPo.setId(objectAo.getId());
        objectPo.setObjectKey(objectAo.getObjectKey());
        objectPo.setObjectName(objectAo.getObjectName());
        objectPo.setObjectDesc(objectAo.getObjectDesc());
        objectPo.setUpdatedBy(IdentityContext.getIdentity().getUserId());
        objectPo.setUpdatedAt(new Date());
        objectMapper.update(objectPo);

        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.OBJECT.getCode(), objectAo.getId()));
        List<ParameterPO> propertyPoList = IObjectConverter.IMPL.propertyListToParameterList(objectPo.getId(), objectAo.getPropertyList());
        if(CollectionUtils.isNotEmpty(propertyPoList)){
            parameterMapper.batchAddParameter(propertyPoList);
        }
        return true;
    }

    @Override
    public ObjectAO queryObject(Long objId) {
        ObjectPO objectPo = objectMapper.queryById(objId);
        ObjectAO objectAo = IObjectConverter.IMPL.poToAo(objectPo);
        List<ParameterPO> propertyList = parameterMapper.getParameterListByVO(new ParameterVO(ParameterSourceTypeEnum.OBJECT.getCode(),objId));
        objectAo.parseProperty(propertyList);
        return objectAo;
    }

    @Override
    public ObjectAO queryObjectInfoByKey(String objectKey) {
        ObjectPO objectPo = objectMapper.queryObjectInfoByKey(objectKey);
        ObjectAO objectAo = IObjectConverter.IMPL.poToAo(objectPo);
        return objectAo;
    }

    @Override
    public List<ObjectVO> queryObjectInfoList() {
        List<ObjectInfoView> objectViewList = objectMapper.queryObjectInfoList();
        List<ObjectVO> objectVoList = IObjectConverter.IMPL.viewListToVoList(objectViewList);
        return objectVoList;
    }

    @Override
    public List<ObjectVO> queryObjectPageList(ObjectQueryParam objectQueryParam) {
        List<ObjectPO> objectPoList = objectMapper.queryByList(objectQueryParam);
        List<ObjectVO> objectVoList = IObjectConverter.IMPL.poListToVoList(objectPoList);
        return objectVoList;
    }
}
