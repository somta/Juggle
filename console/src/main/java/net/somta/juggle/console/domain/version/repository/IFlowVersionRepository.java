package net.somta.juggle.console.domain.version.repository;

import net.somta.juggle.console.domain.version.FlowVersionAO;
import net.somta.juggle.console.domain.version.view.FlowVersionView;
import net.somta.juggle.console.domain.version.vo.FlowVersionQueryVO;
import net.somta.juggle.console.domain.version.vo.FlowVersionVO;

import java.util.List;

/**
 * @author husong
 */
public interface IFlowVersionRepository {
    void deleteFlowVersionById(Long flowVersionId);

    String queryLatestVersion(String flowKey);

    FlowVersionAO getFlowVersionInfo(Long flowVersionId);

    List<FlowVersionView> queryFlowVersionList(FlowVersionQueryVO flowVersionQueryVO);


}
