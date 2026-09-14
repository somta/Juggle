package com.matecoder.juggle.console.infrastructure.mapper;

import com.matecoder.core.base.IBaseMapper;
import com.matecoder.juggle.console.domain.flow.definition.vo.VariableDeleteVO;
import com.matecoder.juggle.console.infrastructure.po.VariableInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author husong
 */
public interface VariableInfoMapper extends IBaseMapper {

    /**
     * 批量新增变量
     * @param variableInfoList
     * @return
     */
    int batchAddVariable(@Param("variableInfoList") List<VariableInfoPO> variableInfoList);

    /**
     * 根据流程定义ID删除变量
     * @param variableDeleteVo
     * @return
     */
    int deleteVariableByFlowDefinitionId(VariableDeleteVO variableDeleteVo);

    /**
     *
     * @param flowDefinitionId
     * @return
     */
    List<VariableInfoPO> queryVariableInfoListByDefinitionId(Long flowDefinitionId);
}
