package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.domain.version.FlowVersionAO;
import net.somta.juggle.console.interfaces.param.FlowVersionPageParam;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;

/**
 * @author Gavin
 */
public interface IFlowVersionService {

    void deleteFlowVersion(Long flowVersionId);

    FlowVersionAO getFlowVersionInfo(Long flowVersionId);

    Boolean updateFlowVersionStatus(FlowVersionAO flowVersionAo);

    FlowVersionAO getFlowVersionInfoByKey(String flowKey, String flowVersion);

    String getLatestDeployVersion(String flowKey);

    PageInfo getFlowVersionPageList(FlowVersionPageParam flowVersionPageParam);

    /**
     * 触发流程
     * @param flowVersionAo
     * @return
     */
    FlowResult triggerFlow(FlowVersionAO flowVersionAo, TriggerDataParam triggerData);

}
