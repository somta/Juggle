package net.somta.juggle.console.domain.flow.repository;

import net.somta.juggle.console.domain.flow.FlowInfoAO;
import net.somta.juggle.console.domain.flow.vo.FlowInfoQueryVO;
import net.somta.juggle.console.domain.flow.vo.FlowInfoVO;

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
