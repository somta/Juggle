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

import net.somta.juggle.console.application.assembler.IDataTypeInfoAssembler;
import net.somta.juggle.console.domain.object.repository.IObjectRepository;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;
import net.somta.juggle.console.application.service.IDataTypeInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * @author husong
 * @since 1.0.0
 */
@Service
public class DataTypeInfoServiceImpl implements IDataTypeInfoService {

    private final IObjectRepository objectRepository;

    public DataTypeInfoServiceImpl(IObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    @Override
    public List<DataTypeOptionDTO> getDataTypeOptions() {
        List<DataTypeOptionDTO> dataTypeOptions = getDefaultDataType();
        List<ObjectVO> objectVoList = objectRepository.queryObjectInfoList();
        List<DataTypeOptionDTO> objectDataTypeList =IDataTypeInfoAssembler.IMPL.objectListToDataTypeOptionDtoList(objectVoList);
        if(CollectionUtils.isNotEmpty(objectDataTypeList)){
            dataTypeOptions.addAll(objectDataTypeList);
        }
        return dataTypeOptions;
    }

    private List<DataTypeOptionDTO> getDefaultDataType(){
        List<DataTypeOptionDTO> defaultDataType = new ArrayList<>();
        defaultDataType.add(new DataTypeOptionDTO(-1L,1,"String","字符串"));
        defaultDataType.add(new DataTypeOptionDTO(-2L,1,"Integer","整数"));
        defaultDataType.add(new DataTypeOptionDTO(-3L,1,"Double","小数"));
        defaultDataType.add(new DataTypeOptionDTO(-4L,1,"Boolean","布尔"));
        defaultDataType.add(new DataTypeOptionDTO(-6L,1,"Date","日期"));
        defaultDataType.add(new DataTypeOptionDTO(-7L,1,"Time","时间"));
        defaultDataType.add(new DataTypeOptionDTO(-10L,2,"List","集合"));
        return defaultDataType;
    }


}
