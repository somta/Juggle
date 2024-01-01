package net.somta.juggle.console.infrastructure.mapper.flow;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoQueryVO;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoVO;
import net.somta.juggle.console.infrastructure.po.flow.FlowDefinitionInfoPO;

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
