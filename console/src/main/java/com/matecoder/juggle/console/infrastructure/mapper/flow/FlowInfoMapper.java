package com.matecoder.juggle.console.infrastructure.mapper.flow;


import com.matecoder.core.base.IBaseMapper;
import com.matecoder.juggle.console.infrastructure.po.flow.FlowInfoPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author husong
 */
public interface FlowInfoMapper extends IBaseMapper {


    int addFlowInfo(FlowInfoPO flowInfoPo);

    FlowInfoPO querySimpleFlowInfo(Long flowId);

    /**
     * 根据流程key查询流程信息
     * @param flowKey
     * @return
     */
    FlowInfoPO queryFlowByFlowKey(@Param("flowKey") String flowKey);


}
