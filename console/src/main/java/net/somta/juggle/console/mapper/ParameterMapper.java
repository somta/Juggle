package net.somta.juggle.console.mapper;


import net.somta.juggle.console.model.Parameter;
import net.somta.juggle.console.model.vo.ParameterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {


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
