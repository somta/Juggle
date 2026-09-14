package com.matecoder.juggle.console.domain.parameter.repository;

import com.matecoder.juggle.console.domain.parameter.ParameterEntity;
import com.matecoder.juggle.console.domain.parameter.vo.ParameterVO;

/**
 * @author Gavin
 */
public interface IParameterRepository {

    /**
     * @param parameterQueryVO
     * @return
     */
    ParameterEntity getParameter(ParameterVO parameterQueryVO);

}
