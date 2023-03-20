package net.somta.juggle.console.service;

import net.somta.juggle.console.model.DataTypeInfo;
import net.somta.juggle.console.model.dto.DataTypeOptionDTO;

import java.util.List;

public interface IDataTypeInfoService {

    /**
     * 获取下拉的选项
     * @return
     */
    List<DataTypeOptionDTO> getDataTypeOptions();
}
