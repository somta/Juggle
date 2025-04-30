package net.somta.juggle.console.application.service.flow;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.domain.flow.version.FlowVersionAO;
import net.somta.juggle.console.interfaces.dto.flow.FlowVersionDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowVersionPageParam;
import net.somta.juggle.common.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;

/**
 * @author Gavin
 */
public interface IFlowVersionService {

    /**
     * Delete flow version
     * @param flowVersionId The deleted flow version ID
     */
    void deleteFlowVersion(Long flowVersionId);

    /**
     * Query flow version information
     * @param flowVersionId The flow version id being queried
     * @return Flow version AO object
     */
    FlowVersionAO getFlowVersionInfo(Long flowVersionId);

    /**
     * Update the status of the process version
     * @param flowVersionAo Flow version AO object
     * @return Update the result value of flow status
     */
    Boolean updateFlowVersionStatus(FlowVersionAO flowVersionAo);

    /**
     * Obtain flow version information based on flow key and version
     * @param flowKey The unique key of the flow
     * @param flowVersion flow version
     * @return Flow version AO object
     */
    FlowVersionAO getFlowVersionInfoByKey(String flowKey, String flowVersion);

    /**
     * Query the latest deployment version
     * @param flowKey The unique key of the flow
     * @return The latest deployment version number
     */
    String getLatestDeployVersion(String flowKey);

    /**
     * Paging data for querying flow versions
     * @param flowVersionPageParam Paging query parameters
     * @return Flow version pagination data
     */
    PageInfo<FlowVersionDTO> getFlowVersionPageList(FlowVersionPageParam flowVersionPageParam);

    /**
     * Trigger a flow
     * @param flowVersionAo Flow version AO object
     * @param triggerData Input parameter data that triggers the flow
     * @return Flow execution results
     */
    FlowResult triggerFlow(FlowVersionAO flowVersionAo, TriggerDataParam triggerData);

}
