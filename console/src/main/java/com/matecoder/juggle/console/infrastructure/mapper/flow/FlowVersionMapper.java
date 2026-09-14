package com.matecoder.juggle.console.infrastructure.mapper.flow;

import com.matecoder.core.base.IBaseMapper;
import com.matecoder.juggle.console.domain.flow.version.view.FlowVersionInfoView;
import com.matecoder.juggle.console.domain.flow.version.view.FlowVersionView;
import com.matecoder.juggle.console.domain.flow.version.vo.FlowVersionQueryVO;

import java.util.List;

/**
 * @author husong
 */
public interface FlowVersionMapper extends IBaseMapper {

    int deleteFlowVersionByFlowId(Long flowId);

    FlowVersionInfoView queryFlowVersionInfoById(Long flowVersionId);

    FlowVersionInfoView queryFlowVersionInfoByKey(FlowVersionQueryVO flowVersionQueryVo);

    List<FlowVersionView> queryFlowVersionList(FlowVersionQueryVO flowVersionQueryVO);

    String queryLatestVersion(String flowKey);


}
