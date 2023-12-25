package net.somta.juggle.console.infrastructure.mapper.flow;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.flow.version.view.FlowVersionView;
import net.somta.juggle.console.domain.flow.version.vo.FlowVersionQueryVO;

import java.util.List;

/**
 * @author husong
 */
public interface FlowVersionMapper extends IBaseMapper {

    int deleteFlowVersionByFlowId(Long flowId);

    List<FlowVersionView> queryFlowVersionList(FlowVersionQueryVO flowVersionQueryVO);

    String queryLatestVersion(String flowKey);


}
