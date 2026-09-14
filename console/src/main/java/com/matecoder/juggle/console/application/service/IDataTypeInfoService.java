package com.matecoder.juggle.console.application.service;

import com.matecoder.juggle.console.interfaces.dto.DataTypeOptionDTO;

import java.util.List;

/**
 * @author husong
 */
public interface IDataTypeInfoService {

    /**
     * Get a list of data types
     * @return Data Type List
     */
    List<DataTypeOptionDTO> getDataTypeOptions();
}
