package net.somta.juggle.console.domain.flow.repository;

import net.somta.juggle.console.infrastructure.po.FlowInfoPO;

public interface IFlowRepository {


    Boolean saveFlow(FlowInfoPO flowInfoPO);


}