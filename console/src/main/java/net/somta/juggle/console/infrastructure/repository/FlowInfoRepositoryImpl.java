package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.flow.FlowInfoAO;
import net.somta.juggle.console.domain.flow.repository.IFlowInfoRepository;
import net.somta.juggle.console.domain.flow.vo.FlowInfoQueryVO;
import net.somta.juggle.console.domain.flow.vo.FlowInfoVO;
import net.somta.juggle.console.infrastructure.converter.IFlowInfoConverter;
import net.somta.juggle.console.infrastructure.converter.IFlowVersionConverter;
import net.somta.juggle.console.infrastructure.mapper.FlowInfoMapper;
import net.somta.juggle.console.infrastructure.mapper.FlowVersionMapper;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.infrastructure.po.FlowVersionPO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteFlowInfoAndFlowVersion() {
        // todo 待完善
        return null;
    }

    @Override
    public FlowInfoAO queryFlowInfo(Long flowInfoId) {
        FlowInfoPO flowInfoPO = flowInfoMapper.queryById(flowInfoId);
        FlowInfoAO flowInfoAO = IFlowInfoConverter.IMPL.poToAo(flowInfoPO);
        return flowInfoAO;
    }

    @Override
    public List<FlowInfoVO> queryFlowInfoList(FlowInfoQueryVO flowInfoQueryVO) {
        List<FlowInfoPO> flowInfoList = flowInfoMapper.queryByList(flowInfoQueryVO);
        List<FlowInfoVO> flowInfoVOList = IFlowInfoConverter.IMPL.poListToVoList(flowInfoList);
        return flowInfoVOList;
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
