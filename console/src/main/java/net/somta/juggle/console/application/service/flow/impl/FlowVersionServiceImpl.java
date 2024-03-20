/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.application.service.flow.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.assembler.flow.IFlowVersionAssembler;
import net.somta.juggle.console.application.service.flow.IFlowRuntimeService;
import net.somta.juggle.console.application.service.flow.IFlowVersionService;
import net.somta.juggle.console.domain.flow.version.FlowVersionAO;
import net.somta.juggle.console.domain.flow.version.repository.IFlowVersionRepository;
import net.somta.juggle.console.domain.flow.version.view.FlowVersionInfoView;
import net.somta.juggle.console.domain.flow.version.view.FlowVersionView;
import net.somta.juggle.console.domain.flow.version.vo.FlowVersionQueryVO;
import net.somta.juggle.console.interfaces.dto.flow.FlowInfoDTO;
import net.somta.juggle.console.interfaces.dto.flow.FlowVersionDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowVersionPageParam;
import net.somta.juggle.common.param.TriggerDataParam;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 * @since 1.0.0
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
        FlowVersionInfoView flowVersionInfoView = flowVersionRepository.queryFlowVersionInfoByKey(flowKey,flowVersion);
        FlowVersionAO flowVersionAo = IFlowVersionAssembler.IMPL.viewToAo(flowVersionInfoView);
        return flowVersionAo;
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
        flow.setFlowContent(flowVersionAo.getFlowContent());
        flow.setInputParams(flowVersionAo.getFlowRuntimeInputParameters());
        flow.setOutputParams(flowVersionAo.getFlowRuntimeOutputParameters());
        flow.setVariables(flowVersionAo.getFlowRuntimeVariables());
        return flowRuntimeService.triggerFlow(flow, flowVersionAo.getFlowType(),triggerData);
    }
}
