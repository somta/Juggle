package com.matecoder.juggle.console.domain.flow.definition.repository;

import com.matecoder.juggle.console.domain.flow.definition.FlowDefinitionAO;
import com.matecoder.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoQueryVO;
import com.matecoder.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoVO;

import java.util.List;

/**
 * @author husong
 */
public interface IFlowDefinitionRepository {
    Long addFlowDefinition(FlowDefinitionAO flowDefinitionAo);

    Boolean deleteFlowDefinitionById(Long flowDefinitionId);

    Boolean updateFlowDefinition(FlowDefinitionAO flowDefinitionAo);

    Boolean saveFlowDefinitionContent(FlowDefinitionAO flowDefinitionAo);

    FlowDefinitionAO queryFlowDefinitionInfo(Long flowDefinitionId);

    FlowDefinitionAO queryFlowDefinitionByKey(String flowKey);

    List<FlowDefinitionInfoVO> queryFlowDefinitionList(FlowDefinitionInfoQueryVO flowDefinitionInfoQueryVo);

}
