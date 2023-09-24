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
    // todo 在仓储层实现整个方法逻辑，然后去掉应用层直接使用mapper的
    ParameterEntity getParameter(ParameterVO parameterQueryVO);

}
