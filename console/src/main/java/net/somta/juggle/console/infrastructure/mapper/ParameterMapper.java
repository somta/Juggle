package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.model.Parameter;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParameterMapper extends IBaseMapper {


    /**
     * 新增参数
     * @param parameter
     * @return
     */
    int addParameter(Parameter parameter);

    /**
     * 批量新增参数
     * @param parameterList
     * @return
     */
    int batchAddParameter(@Param("parameterList") List<Parameter> parameterList);

    /**
     * 根据sourceType和sourceId删除参数
     * @param parameterVO
     * @return
     */
    int deleteParameter(ParameterVO parameterVO);

    /**
     *
     * @param parameterQueryVO
     * @return
     */
    List<Parameter> getParameterListByVO(ParameterVO parameterQueryVO);
}
