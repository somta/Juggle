package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.model.FlowDefinitionInfo;

public interface FlowDefinitionMapper extends IBaseMapper {


    int addFlowDefinitionInfo(FlowDefinitionInfo flowDefinitionInfo);


}
