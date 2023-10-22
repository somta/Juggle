package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.version.view.FlowVersionView;
import net.somta.juggle.console.domain.version.vo.FlowVersionQueryVO;

import java.util.List;

/**
 * @author husong
 */
public interface FlowVersionMapper extends IBaseMapper {


    List<FlowVersionView> queryFlowVersionList(FlowVersionQueryVO flowVersionQueryVO);

    String queryLatestVersion(String flowKey);
}
