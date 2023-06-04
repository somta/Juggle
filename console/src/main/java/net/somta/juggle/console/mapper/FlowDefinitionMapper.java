package net.somta.juggle.console.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.model.FlowDefinitionInfo;
import net.somta.juggle.console.model.param.FlowDefinitionPageParam;
import net.somta.juggle.console.model.param.FlowDefinitionParam;

import java.util.List;

public interface FlowDefinitionMapper extends IBaseMapper {


    int addFlowDefinitionInfo(FlowDefinitionInfo flowDefinitionInfo);


}
