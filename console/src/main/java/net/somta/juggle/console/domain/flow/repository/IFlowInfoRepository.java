package net.somta.juggle.console.domain.flow.repository;

import net.somta.juggle.console.domain.flow.FlowInfoAO;

public interface IFlowInfoRepository {

    Boolean deleteFlowInfoAndFlowVersion();

    //Boolean saveFlow(FlowInfoPO flowInfoPO);

    FlowInfoAO queryFlowInfo(Long flowId);

    Boolean deployFlow(FlowInfoAO flowInfoAO);



}
