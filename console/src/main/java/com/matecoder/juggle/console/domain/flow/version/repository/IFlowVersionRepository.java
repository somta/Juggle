package com.matecoder.juggle.console.domain.flow.version.repository;

import com.matecoder.juggle.console.domain.flow.version.FlowVersionAO;
import com.matecoder.juggle.console.domain.flow.version.view.FlowVersionInfoView;
import com.matecoder.juggle.console.domain.flow.version.view.FlowVersionView;
import com.matecoder.juggle.console.domain.flow.version.vo.FlowVersionQueryVO;

import java.util.List;

/**
 * @author husong
 */
public interface IFlowVersionRepository {
    void deleteFlowVersionById(Long flowVersionId);

    Boolean updateFlowVersion(FlowVersionAO flowVersionAo);

    String queryLatestVersion(String flowKey);

    FlowVersionAO getFlowVersionInfo(Long flowVersionId);

    FlowVersionInfoView queryFlowVersionInfoByKey(String flowKey, String flowVersion);

    List<FlowVersionView> queryFlowVersionList(FlowVersionQueryVO flowVersionQueryVo);

    void invalidateFlowCache(String flowKey, String flowVersion);


}
