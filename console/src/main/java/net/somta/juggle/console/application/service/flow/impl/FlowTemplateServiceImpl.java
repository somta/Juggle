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
import net.somta.juggle.console.application.assembler.flow.IFlowTemplateAssembler;
import net.somta.juggle.console.application.service.flow.IFlowDefinitionService;
import net.somta.juggle.console.application.service.flow.IFlowTemplateService;
import net.somta.juggle.console.domain.flow.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.flow.template.FlowTemplateAO;
import net.somta.juggle.console.domain.flow.template.repository.IFlowTemplateRepository;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateInfoVO;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateQueryVO;
import net.somta.juggle.console.interfaces.dto.flow.FlowTemplateInfoDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowTemplatePageParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 * @since 1.0.0
 */
@Service
public class FlowTemplateServiceImpl implements IFlowTemplateService {

    private final IFlowTemplateRepository flowTemplateRepository;
    private final IFlowDefinitionService flowDefinitionService;

    public FlowTemplateServiceImpl(IFlowTemplateRepository flowTemplateRepository, IFlowDefinitionService flowDefinitionService) {
        this.flowTemplateRepository = flowTemplateRepository;
        this.flowDefinitionService = flowDefinitionService;
    }

    @Override
    public Boolean deleteFlowTemplate(Long templateId) {
        return flowTemplateRepository.deleteFlowTemplateById(templateId);
    }

    @Override
    public PageInfo getFlowTemplatePageList(FlowTemplatePageParam flowTemplatePageParam) {
        FlowTemplateQueryVO flowTemplateQueryVO = IFlowTemplateAssembler.IMPL.paramToVo(flowTemplatePageParam);
        Page<FlowTemplateInfoDTO> page = PageHelper.startPage(flowTemplatePageParam.getPageNum(), flowTemplatePageParam.getPageSize());
        List<FlowTemplateInfoVO> flowTemplateList = flowTemplateRepository.queryFlowTemplateList(flowTemplateQueryVO);
        PageInfo pageInfo = new PageInfo(IFlowTemplateAssembler.IMPL.voListToDtoList(flowTemplateList));
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public Long applyFlowTemplate(Long templateId) {
        FlowTemplateAO flowTemplateAo = flowTemplateRepository.queryFlowTemplateId(templateId);
        FlowDefinitionAO flowDefinitionAo = new FlowDefinitionAO();
        flowDefinitionAo.setFlowName(flowTemplateAo.getTemplateName());
        flowDefinitionAo.setFlowType(flowDefinitionAo.getFlowType());
        flowDefinitionAo.setFlowKey(flowDefinitionAo.generateFlowKey());
        flowDefinitionAo.setFlowContent(flowTemplateAo.getTemplateContent());
        flowDefinitionAo.setRemark(flowTemplateAo.getTemplateRemark());

        flowDefinitionAo.setParameterEntity(flowTemplateAo.initParameterEntity());
        flowDefinitionAo.setVariableInfoList(flowTemplateAo.getFlowVariableInfoList());
        Long flowDefinitionId = flowDefinitionService.createFlowDefinitionByTemplate(flowDefinitionAo);
        return flowDefinitionId;
    }
}
