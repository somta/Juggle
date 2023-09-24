package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;

/**
 * @author Gavin
 */
public interface FlowDefinitionMapper extends IBaseMapper {


    int addFlowDefinitionInfo(FlowDefinitionInfoPO flowDefinitionInfoPO);


    FlowDefinitionInfoPO queryFlowDefinitionByKey(String flowKey);
}
