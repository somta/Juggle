package com.matecoder.juggle.console.infrastructure.mapper.flow;


import com.matecoder.core.base.IBaseMapper;
import com.matecoder.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoQueryVO;
import com.matecoder.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoVO;
import com.matecoder.juggle.console.infrastructure.po.flow.FlowDefinitionInfoPO;

import java.util.List;

/**
 * @author husong
 */
public interface FlowDefinitionMapper extends IBaseMapper {

    /**
     * Add flow definition information
     * @param flowDefinitionInfoPo Flow Definition Information PO Object
     * @return Flow definition id
     */
    Long addFlowDefinitionInfo(FlowDefinitionInfoPO flowDefinitionInfoPo);

    /**
     * Query the flow PO object based on the flow key
     * @param flowKey Flow key
     * @return Flow PO object
     */
    FlowDefinitionInfoPO queryFlowDefinitionByKey(String flowKey);

    /**
     * Query flow definition information list
     * @param flowDefinitionInfoQueryVO Flow Definition Query Object
     * @return Flow Definition List
     */
    List<FlowDefinitionInfoVO> queryFlowDefinitionList(FlowDefinitionInfoQueryVO flowDefinitionInfoQueryVO);
}
