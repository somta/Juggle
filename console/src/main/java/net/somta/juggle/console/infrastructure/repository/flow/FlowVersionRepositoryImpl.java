package net.somta.juggle.console.infrastructure.repository.flow;

import net.somta.juggle.console.domain.version.FlowVersionAO;
import net.somta.juggle.console.domain.version.repository.IFlowVersionRepository;
import net.somta.juggle.console.domain.version.view.FlowVersionView;
import net.somta.juggle.console.domain.version.vo.FlowVersionQueryVO;
import net.somta.juggle.console.infrastructure.converter.flow.IFlowVersionConverter;
import net.somta.juggle.console.infrastructure.mapper.flow.FlowVersionMapper;
import net.somta.juggle.console.infrastructure.po.flow.FlowVersionPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author husong
 */
@Repository
public class FlowVersionRepositoryImpl implements IFlowVersionRepository {

    private final FlowVersionMapper flowVersionMapper;

    public FlowVersionRepositoryImpl(FlowVersionMapper flowVersionMapper) {
        this.flowVersionMapper = flowVersionMapper;
    }

    @Override
    public void deleteFlowVersionById(Long flowVersionId) {
        flowVersionMapper.deleteById(flowVersionId);
    }

    @Override
    public Boolean updateFlowVersion(FlowVersionAO flowVersionAo) {
        FlowVersionPO flowVersionPo = IFlowVersionConverter.IMPL.aoToPo(flowVersionAo);
        flowVersionMapper.update(flowVersionPo);
        return true;
    }

    @Override
    public String queryLatestVersion(String flowKey) {
        return flowVersionMapper.queryLatestVersion(flowKey);
    }

    @Override
    public FlowVersionAO getFlowVersionInfo(Long flowVersionId) {
        FlowVersionPO flowVersionPo = flowVersionMapper.queryById(flowVersionId);
        FlowVersionAO flowVersionAo = IFlowVersionConverter.IMPL.poToAo(flowVersionPo);
        return flowVersionAo;
    }

    @Override
    public List<FlowVersionView> queryFlowVersionList(FlowVersionQueryVO flowVersionQueryVO) {
        List<FlowVersionView> flowVersionViewList = flowVersionMapper.queryFlowVersionList(flowVersionQueryVO);
        return flowVersionViewList;
    }
}
