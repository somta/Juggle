package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.flow.FlowInfoAO;
import net.somta.juggle.console.domain.flow.repository.IFlowInfoRepository;
import net.somta.juggle.console.domain.flow.vo.FlowInfoQueryVO;
import net.somta.juggle.console.domain.flow.vo.FlowInfoVO;
import net.somta.juggle.console.domain.version.enums.FlowVersionStatusEnum;
import net.somta.juggle.console.infrastructure.converter.IFlowInfoConverter;
import net.somta.juggle.console.infrastructure.converter.IFlowVersionConverter;
import net.somta.juggle.console.infrastructure.mapper.FlowInfoMapper;
import net.somta.juggle.console.infrastructure.mapper.FlowVersionMapper;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.infrastructure.po.FlowVersionPO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author husong
 */
@Repository
public class FlowInfoRepositoryImpl implements IFlowInfoRepository {

    private final FlowInfoMapper flowInfoMapper;
    private final FlowVersionMapper flowVersionMapper;

    public FlowInfoRepositoryImpl(FlowInfoMapper flowInfoMapper, FlowVersionMapper flowVersionMapper) {
        this.flowInfoMapper = flowInfoMapper;
        this.flowVersionMapper = flowVersionMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteFlowInfoAndFlowVersion(Long flowId) {
        flowInfoMapper.deleteById(flowId);
        flowVersionMapper.deleteFlowVersionByFlowId(flowId);
        return true;
    }

    @Override
    public FlowInfoAO queryFlowInfo(Long flowInfoId) {
        FlowInfoPO flowInfoPo = flowInfoMapper.queryById(flowInfoId);
        FlowInfoAO flowInfoAo = IFlowInfoConverter.IMPL.poToAo(flowInfoPo);
        return flowInfoAo;
    }

    @Override
    public List<FlowInfoVO> queryFlowInfoList(FlowInfoQueryVO flowInfoQueryVO) {
        List<FlowInfoPO> flowInfoList = flowInfoMapper.queryByList(flowInfoQueryVO);
        List<FlowInfoVO> flowInfoVOList = IFlowInfoConverter.IMPL.poListToVoList(flowInfoList);
        return flowInfoVOList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deployFlow(FlowInfoAO flowInfoAo) {
        Date currentDate = new Date();
        FlowInfoPO flowInfoPo = flowInfoMapper.queryFlowByFlowKey(flowInfoAo.getFlowKey());
        if(flowInfoPo == null){
            flowInfoPo = IFlowInfoConverter.IMPL.aoToPo(flowInfoAo);
            flowInfoPo.setCreatedAt(currentDate);
            flowInfoMapper.addFlowInfo(flowInfoPo);
        }

        FlowVersionPO flowVersionPo = IFlowVersionConverter.IMPL.aoToPo(flowInfoAo);
        flowVersionPo.setFlowId(flowInfoPo.getId());
        flowVersionPo.setFlowVersionStatus(FlowVersionStatusEnum.DISABLED.getCode());
        flowVersionPo.setCreatedAt(currentDate);
        flowVersionMapper.add(flowVersionPo);
        return true;
    }
}
