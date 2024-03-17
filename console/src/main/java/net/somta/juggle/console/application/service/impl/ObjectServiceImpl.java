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
package net.somta.juggle.console.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.assembler.IObjectAssembler;
import net.somta.juggle.console.application.service.IObjectService;
import net.somta.juggle.console.domain.object.ObjectAO;
import net.somta.juggle.console.domain.object.repository.IObjectRepository;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.interfaces.dto.ObjectDTO;
import net.somta.juggle.console.interfaces.dto.ObjectInfoDTO;
import net.somta.juggle.console.interfaces.param.ObjectAddParam;
import net.somta.juggle.console.interfaces.param.ObjectQueryParam;
import net.somta.juggle.console.interfaces.param.ObjectUpdateParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 * @since 1.0.0
 */
@Service
public class ObjectServiceImpl implements IObjectService {
    private final IObjectRepository objRepository;

    public ObjectServiceImpl(IObjectRepository objRepository) {
        this.objRepository = objRepository;
    }

    @Override
    public Boolean addObject(ObjectAddParam objectAddParam) {
        ObjectAO objectAo = IObjectAssembler.IMPL.paramToAo(objectAddParam);
        objectAo.initPropertyList(objectAddParam.getProps());
        return objRepository.addObject(objectAo);
    }

    @Override
    public Boolean deleteObject(Long objectId) {
        return objRepository.deleteObjectById(objectId);
    }

    @Override
    public Boolean updateObject(ObjectUpdateParam objectUpdateParam) {
        ObjectAO objectAO = IObjectAssembler.IMPL.paramToAo(objectUpdateParam);
        objectAO.initPropertyList(objectUpdateParam.getProps());
        return objRepository.updateObject(objectAO);
    }

    @Override
    public ObjectAO getObjectInfoByKey(String objectKey) {
        return objRepository.queryObjectInfoByKey(objectKey);
    }

    @Override
    public ObjectInfoDTO getObjectInfo(Long objectId) {
        ObjectAO objectAo = objRepository.queryObject(objectId);
        ObjectInfoDTO objectInfoDto = IObjectAssembler.IMPL.aoToDto(objectAo);
        return objectInfoDto;
    }

    @Override
    public List<ObjectDTO> getObjectInfoList() {
        List<ObjectVO> objectVoList = objRepository.queryObjectInfoList();
        List<ObjectDTO> objList = IObjectAssembler.IMPL.voListToDtoList(objectVoList);
        return objList;
    }

    @Override
    public PageInfo getObjectPageList(ObjectQueryParam objectQueryParam) {
        Page<ObjectDTO> page = PageHelper.startPage(objectQueryParam.getPageNum(), objectQueryParam.getPageSize());
        List<ObjectVO> objectVoList = objRepository.queryObjectPageList(objectQueryParam);
        List<ObjectDTO> objList = IObjectAssembler.IMPL.voListToDtoList(objectVoList);
        PageInfo pageInfo = new PageInfo(objList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
}
