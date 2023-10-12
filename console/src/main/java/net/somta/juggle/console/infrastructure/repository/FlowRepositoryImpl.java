package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.flow.FlowAO;
import net.somta.juggle.console.domain.flow.repository.IFlowRepository;
import net.somta.juggle.console.infrastructure.converter.IFlowInfoConverter;
import net.somta.juggle.console.infrastructure.converter.IFlowVersionConverter;
import net.somta.juggle.console.infrastructure.mapper.FlowInfoMapper;
import net.somta.juggle.console.infrastructure.mapper.FlowVersionMapper;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.infrastructure.po.FlowVersionPO;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author husong
 */
@Component
public class FlowRepositoryImpl implements IFlowRepository {

    private final FlowInfoMapper flowInfoMapper;
    private final FlowVersionMapper flowVersionMapper;

    public FlowRepositoryImpl(FlowInfoMapper flowInfoMapper, FlowVersionMapper flowVersionMapper) {
        this.flowInfoMapper = flowInfoMapper;
        this.flowVersionMapper = flowVersionMapper;
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
        Date currentDate = new Date();
        FlowInfoPO flowInfoPO = flowInfoMapper.queryFlowByFlowKey(flowAO.getFlowKey());
        if(flowInfoPO == null){
            flowInfoPO = IFlowInfoConverter.IMPL.aoToPo(flowAO);
            flowInfoPO.setCreatedAt(currentDate);
            flowInfoMapper.addFlowInfo(flowInfoPO);
        }

        FlowVersionPO flowVersionPO = IFlowVersionConverter.IMPL.aoToPo(flowAO);
        flowVersionPO.setFlowId(flowInfoPO.getId());
        flowVersionPO.setCreatedAt(currentDate);
        flowVersionMapper.add(flowVersionPO);
        return true;
    }
}
