package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.flow.FlowInfoAO;
import net.somta.juggle.console.domain.flow.repository.IFlowInfoRepository;
import net.somta.juggle.console.infrastructure.converter.IFlowInfoConverter;
import net.somta.juggle.console.infrastructure.converter.IFlowVersionConverter;
import net.somta.juggle.console.infrastructure.mapper.FlowInfoMapper;
import net.somta.juggle.console.infrastructure.mapper.FlowVersionMapper;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.infrastructure.po.FlowVersionPO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author husong
 */
@Component
public class FlowInfoRepositoryImpl implements IFlowInfoRepository {

    private final FlowInfoMapper flowInfoMapper;
    private final FlowVersionMapper flowVersionMapper;

    public FlowInfoRepositoryImpl(FlowInfoMapper flowInfoMapper, FlowVersionMapper flowVersionMapper) {
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
    public Boolean deleteFlowInfoAndFlowVersion() {
        // todo 待完善
        return null;
    }

    @Override
    public FlowInfoAO queryFlowInfo(Long flowId) {
        //todo 待完善
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deployFlow(FlowInfoAO flowInfoAO) {
        Date currentDate = new Date();
        FlowInfoPO flowInfoPO = flowInfoMapper.queryFlowByFlowKey(flowInfoAO.getFlowKey());
        if(flowInfoPO == null){
            flowInfoPO = IFlowInfoConverter.IMPL.aoToPo(flowInfoAO);
            flowInfoPO.setCreatedAt(currentDate);
            flowInfoMapper.addFlowInfo(flowInfoPO);
        }

        FlowVersionPO flowVersionPO = IFlowVersionConverter.IMPL.aoToPo(flowInfoAO);
        flowVersionPO.setFlowId(flowInfoPO.getId());
        flowVersionPO.setCreatedAt(currentDate);
        flowVersionMapper.add(flowVersionPO);
        return true;
    }
}
