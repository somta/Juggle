package net.somta.juggle.console.application.service;

import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;

import java.util.List;

/**
 * @author husong
 */
public interface IDataTypeInfoService {

    /**
     * Get a list of data types
     * @param dataTypeClassify Data type classification
     * @return Data Type List
     */
    List<DataTypeOptionDTO> getDataTypeOptions(Integer dataTypeClassify);
}
