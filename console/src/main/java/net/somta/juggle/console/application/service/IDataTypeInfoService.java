package net.somta.juggle.console.application.service;

import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;

import java.util.List;

public interface IDataTypeInfoService {

    /**
     * 获取下拉的选项
     * @return
     */
    List<DataTypeOptionDTO> getDataTypeOptions(Integer dataTypeClassify);
}
