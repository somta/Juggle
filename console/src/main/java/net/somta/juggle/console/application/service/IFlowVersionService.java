package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.domain.version.FlowVersionAO;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.interfaces.param.FlowVersionPageParam;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;

/**
 * @author Gavin
 */
public interface IFlowVersionService {

    void deleteFlowVersion(Long flowVersionId);
    FlowVersionAO getFlowVersionInfo(Long flowVersionId);

    Boolean updateFlowVersionStatus(FlowVersionAO flowVersionAO);

    FlowVersionAO getFlowVersionInfoByKey(String flowKey, String flowVersion);

    PageInfo getFlowVersionPageList(FlowVersionPageParam flowVersionPageParam);

    /**
     * 触发流程
     * @param flowVersionAO
     * @return
     */
    FlowResult triggerFlow(FlowVersionAO flowVersionAO, TriggerDataParam triggerData);

}
