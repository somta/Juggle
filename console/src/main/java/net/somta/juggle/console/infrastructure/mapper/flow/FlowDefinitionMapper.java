package net.somta.juggle.console.infrastructure.mapper.flow;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoQueryVO;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoVO;
import net.somta.juggle.console.infrastructure.po.flow.FlowDefinitionInfoPO;

import java.util.List;

/**
 * @author Gavin
 */
public interface FlowDefinitionMapper extends IBaseMapper {


    int addFlowDefinitionInfo(FlowDefinitionInfoPO flowDefinitionInfoPo);

    FlowDefinitionInfoPO queryFlowDefinitionByKey(String flowKey);

    List<FlowDefinitionInfoVO> queryFlowDefinitionList(FlowDefinitionInfoQueryVO flowDefinitionInfoQueryVO);
}
