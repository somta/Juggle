package com.matecoder.juggle.console.domain.flow.flowinfo.repository;

import com.matecoder.juggle.console.domain.flow.flowinfo.FlowInfoAO;
import com.matecoder.juggle.console.domain.flow.flowinfo.vo.FlowInfoQueryVO;
import com.matecoder.juggle.console.domain.flow.flowinfo.vo.FlowInfoVO;

import java.util.List;

/**
 * @author husong
 */
public interface IFlowInfoRepository {

    Boolean deleteFlowInfoAndFlowVersion(Long flowId);

    FlowInfoAO queryFlowInfo(Long flowInfoId);

    List<FlowInfoVO> queryFlowInfoList(FlowInfoQueryVO flowInfoQueryVo);

    Boolean deployFlow(FlowInfoAO flowInfoAo);
}
