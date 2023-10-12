package net.somta.juggle.console.domain.flow.repository;

import net.somta.juggle.console.domain.flow.FlowAO;

public interface IFlowRepository {


    //Boolean saveFlow(FlowInfoPO flowInfoPO);


    Boolean deployFlow(FlowAO flowAO);
}
