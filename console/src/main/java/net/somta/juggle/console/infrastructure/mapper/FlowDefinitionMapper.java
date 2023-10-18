package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.definition.vo.FlowDefinitionInfoQueryVO;
import net.somta.juggle.console.domain.definition.vo.FlowDefinitionInfoVO;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;

import java.util.List;

/**
 * @author Gavin
 */
public interface FlowDefinitionMapper extends IBaseMapper {


    int addFlowDefinitionInfo(FlowDefinitionInfoPO flowDefinitionInfoPO);


    FlowDefinitionInfoPO queryFlowDefinitionByKey(String flowKey);

    List<FlowDefinitionInfoVO> queryFlowDefinitionList(FlowDefinitionInfoQueryVO flowDefinitionInfoQueryVO);
}
