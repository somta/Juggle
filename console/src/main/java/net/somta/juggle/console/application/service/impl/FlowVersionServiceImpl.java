package net.somta.juggle.console.application.service.impl;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.service.IFlowRuntimeService;
import net.somta.juggle.console.application.service.IFlowVersionService;
import net.somta.juggle.console.domain.version.FlowVersionAO;
import net.somta.juggle.console.domain.version.repository.IFlowVersionRepository;
import net.somta.juggle.console.interfaces.param.FlowVersionPageParam;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowResult;
import org.springframework.stereotype.Service;

/**
 * @author husong
 */
@Service
public class FlowVersionServiceImpl implements IFlowVersionService {

    private final IFlowVersionRepository flowVersionRepository;
    private final IFlowRuntimeService flowRuntimeService;

    public FlowVersionServiceImpl(IFlowVersionRepository flowVersionRepository, IFlowRuntimeService flowRuntimeService) {
        this.flowVersionRepository = flowVersionRepository;
        this.flowRuntimeService = flowRuntimeService;
    }

    @Override
    public void deleteFlowVersion(Long flowVersionId) {

    }

    @Override
    public FlowVersionAO getFlowVersionInfo(Long flowVersionId) {
        return null;
    }

    @Override
    public Boolean updateFlowVersionStatus(FlowVersionAO flowVersionAO) {
        return null;
    }

    @Override
    public FlowVersionAO getFlowVersionInfoByKey(String flowKey, String flowVersion) {
        return null;
    }

    @Override
    public PageInfo getFlowVersionPageList(FlowVersionPageParam flowVersionPageParam) {
        return null;
    }

    @Override
    public FlowResult triggerFlow(FlowVersionAO flowVersionAO, TriggerDataParam triggerData) {
        Flow flow = new Flow();
        flow.setFlowKey(flowVersionAO.getFlowKey());
        flow.setFlowName(flowVersionAO.getFlowName());
       /* flow.setFlowContent(flowInfoPO.getFlowContent());

        String inputParameters = flowInfoPO.getInputs();
        if(StringUtils.isNotEmpty(inputParameters)){
            List<InputParameter> inputParams = JsonSerializeHelper.deserialize(inputParameters,List.class,InputParameter.class);
            flow.setInputParams(inputParams);
        }

        String outputParameters = flowInfoPO.getOutputs();
        if(StringUtils.isNotEmpty(outputParameters)){
            List<OutputParameter> outputParams = JsonSerializeHelper.deserialize(outputParameters,List.class,OutputParameter.class);
            flow.setOutputParams(outputParams);
        }

        String variables = flowInfoPO.getVariables();
        if(StringUtils.isNotEmpty(variables)){
            List<Variable> variableList = JsonSerializeHelper.deserialize(variables,List.class,Variable.class);
            flow.setVariables(variableList);
        }*/

        return flowRuntimeService.triggerFlow(flow, flowVersionAO.getFlowType(),triggerData);
    }
}
