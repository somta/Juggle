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
package net.somta.juggle.console.infrastructure.repository.flow;

import net.somta.juggle.console.domain.flow.template.FlowTemplateAO;
import net.somta.juggle.console.domain.flow.template.repository.IFlowTemplateRepository;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateInfoVO;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateQueryVO;
import net.somta.juggle.console.domain.flow.version.FlowVersionAO;
import net.somta.juggle.console.domain.flow.version.repository.IFlowVersionRepository;
import net.somta.juggle.console.domain.flow.version.view.FlowVersionView;
import net.somta.juggle.console.domain.flow.version.vo.FlowVersionQueryVO;
import net.somta.juggle.console.infrastructure.converter.flow.IFlowTemplateConverter;
import net.somta.juggle.console.infrastructure.converter.flow.IFlowVersionConverter;
import net.somta.juggle.console.infrastructure.mapper.flow.FlowTemplateMapper;
import net.somta.juggle.console.infrastructure.mapper.flow.FlowVersionMapper;
import net.somta.juggle.console.infrastructure.po.flow.FlowTemplatePO;
import net.somta.juggle.console.infrastructure.po.flow.FlowVersionPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author husong
 * @since 1.0.0
 */
@Repository
public class FlowTemplateRepositoryImpl implements IFlowTemplateRepository {

    private final FlowTemplateMapper flowTemplateMapper;

    public FlowTemplateRepositoryImpl(FlowTemplateMapper flowTemplateMapper) {
        this.flowTemplateMapper = flowTemplateMapper;
    }


    @Override
    public Boolean deleteFlowTemplateById(Long templateId) {
        flowTemplateMapper.deleteById(templateId);
        return true;
    }

    @Override
    public FlowTemplateAO queryFlowTemplateId(Long templateId) {
        FlowTemplatePO flowTemplatePo = flowTemplateMapper.queryById(templateId);
        return IFlowTemplateConverter.IMPL.poToAo(flowTemplatePo);
    }

    @Override
    public List<FlowTemplateInfoVO> queryFlowTemplateList(FlowTemplateQueryVO flowTemplateQueryVO) {
        List<FlowTemplatePO> templateList = flowTemplateMapper.queryByList(flowTemplateQueryVO);
        return IFlowTemplateConverter.IMPL.poListToVoList(templateList);
    }
}
