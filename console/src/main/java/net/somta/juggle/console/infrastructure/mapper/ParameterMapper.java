package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Gavin
 */
public interface ParameterMapper extends IBaseMapper {


    /**
     * 新增参数
     * @param parameterPO
     * @return
     */
    int addParameter(ParameterPO parameterPO);

    /**
     * 批量新增参数
     * @param parameterList
     * @return
     */
    int batchAddParameter(@Param("parameterList") List<ParameterPO> parameterList);

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
    List<ParameterPO> getParameterListByVO(ParameterVO parameterQueryVO);
}
