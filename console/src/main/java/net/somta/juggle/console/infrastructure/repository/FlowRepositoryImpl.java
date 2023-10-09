package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.flow.FlowAO;
import net.somta.juggle.console.domain.flow.repository.IFlowRepository;
import net.somta.juggle.console.infrastructure.mapper.FlowMapper;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import org.springframework.stereotype.Component;

/**
 * @author husong
 */
@Component
public class FlowRepositoryImpl implements IFlowRepository {

    private final FlowMapper flowMapper;

    public FlowRepositoryImpl(FlowMapper flowMapper) {
        this.flowMapper = flowMapper;
    }

    /*@Override
    public Boolean saveFlow(FlowInfoPO flowInfoPO) {
        FlowInfoPO flow = flowMapper.queryFlowByFlowKey(flowInfoPO.getFlowKey());
        if(flow != null){
            flowMapper.update(flow);
        } else {
            flowMapper.add(flowInfoPO);
        }
        return true;
    }*/

    @Override
    public Boolean deployFlow(FlowAO flowAO) {
        return true;
    }
}
