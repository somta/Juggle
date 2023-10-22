package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.version.FlowVersionAO;
import net.somta.juggle.console.domain.version.repository.IFlowVersionRepository;
import net.somta.juggle.console.domain.version.view.FlowVersionView;
import net.somta.juggle.console.domain.version.vo.FlowVersionQueryVO;
import net.somta.juggle.console.domain.version.vo.FlowVersionVO;
import net.somta.juggle.console.infrastructure.converter.IFlowVersionConverter;
import net.somta.juggle.console.infrastructure.mapper.FlowVersionMapper;
import net.somta.juggle.console.infrastructure.po.FlowVersionPO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author husong
 */
@Component
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
    public String queryLatestVersion(String flowKey) {
        return flowVersionMapper.queryLatestVersion(flowKey);
    }

    @Override
    public FlowVersionAO getFlowVersionInfo(Long flowVersionId) {
        FlowVersionPO flowVersionPO = flowVersionMapper.queryById(flowVersionId);
        FlowVersionAO flowVersionAO = IFlowVersionConverter.IMPL.poToAo(flowVersionPO);
        return flowVersionAO;
    }

    @Override
    public List<FlowVersionView> queryFlowVersionList(FlowVersionQueryVO flowVersionQueryVO) {
        List<FlowVersionView> flowVersionViewList = flowVersionMapper.queryFlowVersionList(flowVersionQueryVO);
        return flowVersionViewList;
    }
}
