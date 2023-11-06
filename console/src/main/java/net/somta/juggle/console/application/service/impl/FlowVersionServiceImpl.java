package net.somta.juggle.console.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.assembler.IFlowInfoAssembler;
import net.somta.juggle.console.application.assembler.IFlowVersionAssembler;
import net.somta.juggle.console.application.service.IFlowRuntimeService;
import net.somta.juggle.console.application.service.IFlowVersionService;
import net.somta.juggle.console.domain.flow.vo.FlowInfoQueryVO;
import net.somta.juggle.console.domain.flow.vo.FlowInfoVO;
import net.somta.juggle.console.domain.version.FlowVersionAO;
import net.somta.juggle.console.domain.version.repository.IFlowVersionRepository;
import net.somta.juggle.console.domain.version.view.FlowVersionView;
import net.somta.juggle.console.domain.version.vo.FlowVersionQueryVO;
import net.somta.juggle.console.domain.version.vo.FlowVersionVO;
import net.somta.juggle.console.interfaces.dto.FlowInfoDTO;
import net.somta.juggle.console.interfaces.dto.FlowVersionDTO;
import net.somta.juggle.console.interfaces.param.FlowVersionPageParam;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowResult;
import org.springframework.stereotype.Service;

import java.util.List;

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
        flowVersionRepository.deleteFlowVersionById(flowVersionId);
    }

    @Override
    public FlowVersionAO getFlowVersionInfo(Long flowVersionId) {
        return flowVersionRepository.getFlowVersionInfo(flowVersionId);
    }

    @Override
    public Boolean updateFlowVersionStatus(FlowVersionAO flowVersionAo) {
        return flowVersionRepository.updateFlowVersion(flowVersionAo);
    }

    @Override
    public FlowVersionAO getFlowVersionInfoByKey(String flowKey, String flowVersion) {
        return null;
    }

    @Override
    public String getLatestDeployVersion(String flowKey) {
        return flowVersionRepository.queryLatestVersion(flowKey);
    }

    @Override
    public PageInfo getFlowVersionPageList(FlowVersionPageParam flowVersionPageParam) {
        FlowVersionQueryVO flowVersionQueryVO = IFlowVersionAssembler.IMPL.paramToVo(flowVersionPageParam);
        Page<FlowInfoDTO> page = PageHelper.startPage(flowVersionPageParam.getPageNum(), flowVersionPageParam.getPageSize());
        List<FlowVersionView> flowVersionViewList = flowVersionRepository.queryFlowVersionList(flowVersionQueryVO);
        List<FlowVersionDTO> flowVersionDTOList = IFlowVersionAssembler.IMPL.viewListToDtoList(flowVersionViewList);
        PageInfo pageInfo = new PageInfo(flowVersionDTOList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public FlowResult triggerFlow(FlowVersionAO flowVersionAo, TriggerDataParam triggerData) {
        Flow flow = new Flow();
        flow.setFlowKey(flowVersionAo.getFlowKey());
        flow.setFlowName(flowVersionAo.getFlowName());
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

        return flowRuntimeService.triggerFlow(flow, flowVersionAo.getFlowType(),triggerData);
    }
}
