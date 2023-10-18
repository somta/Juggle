package net.somta.juggle.console.application.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.core.exception.BizException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.application.service.IFlowRuntimeService;
import net.somta.juggle.console.domain.flow.FlowInfoAO;
import net.somta.juggle.console.domain.flow.repository.IFlowInfoRepository;
import net.somta.juggle.console.infrastructure.mapper.FlowInfoMapper;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.*;
import net.somta.juggle.console.application.service.IFlowInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.somta.juggle.console.domain.flow.enums.FlowErrorEnum.ENABLE_FLOW_NOT_DELETE;

@Service
public class FlowInfoInfoServiceImpl extends BaseServiceImpl<FlowInfoPO> implements IFlowInfoService {

    @Autowired
    private FlowInfoMapper flowInfoMapper;

    @Autowired
    private IFlowRuntimeService flowRuntimeService;

    @Autowired
    private IFlowInfoRepository flowInfoRepository;

    @Override
    public IBaseMapper getMapper() {
        return flowInfoMapper;
    }

    @Override
    public Boolean deleteFlowInfo(Long flowId) {
        FlowInfoAO flowInfoAO = flowInfoRepository.queryFlowInfo(flowId);
        if(flowInfoAO.isExistEnableVersion()){
            throw new BizException(ENABLE_FLOW_NOT_DELETE);
        }
        return flowInfoRepository.deleteFlowInfoAndFlowVersion();
    }

    @Override
    public FlowResult triggerFlow(FlowInfoPO flowInfoPO, TriggerDataParam triggerData) {
        Flow flow = new Flow();
        flow.setFlowKey(flowInfoPO.getFlowKey());
        flow.setFlowName(flowInfoPO.getFlowName());
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

        return flowRuntimeService.triggerFlow(flow, flowInfoPO.getFlowType(),triggerData);
    }

    @Override
    public FlowInfoPO getFlowByFlowKey(String flowKey) {
        return flowInfoMapper.queryFlowByFlowKey(flowKey);
    }



}
