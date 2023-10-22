package net.somta.juggle.console.domain.parameter.repository;

import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;

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
