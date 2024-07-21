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

import net.somta.juggle.console.domain.flow.version.FlowVersionAO;
import net.somta.juggle.console.domain.flow.version.repository.IFlowVersionRepository;
import net.somta.juggle.console.domain.flow.version.view.FlowVersionInfoView;
import net.somta.juggle.console.domain.flow.version.view.FlowVersionView;
import net.somta.juggle.console.domain.flow.version.vo.FlowVersionQueryVO;
import net.somta.juggle.console.infrastructure.converter.flow.IFlowVersionConverter;
import net.somta.juggle.console.infrastructure.mapper.flow.FlowVersionMapper;
import net.somta.juggle.console.infrastructure.po.flow.FlowVersionPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author husong
 * @since 1.0.0
 */
@Repository
public class FlowVersionRepositoryImpl implements IFlowVersionRepository {

    private final FlowVersionMapper flowVersionMapper;

    public FlowVersionRepositoryImpl(FlowVersionMapper flowVersionMapper) {
        this.flowVersionMapper = flowVersionMapper;
    }

    @Override
    public void deleteFlowVersionById(Long flowVersionId) {
        FlowVersionPO flowVersionPo = new FlowVersionPO();
        flowVersionPo.setId(flowVersionId);
        flowVersionPo.setDeleted(1);
        flowVersionMapper.update(flowVersionPo);
    }

    @Override
    public Boolean updateFlowVersion(FlowVersionAO flowVersionAo) {
        FlowVersionPO flowVersionPo = IFlowVersionConverter.IMPL.aoToPo(flowVersionAo);
        flowVersionMapper.update(flowVersionPo);
        return true;
    }

    @Override
    public String queryLatestVersion(String flowKey) {
        return flowVersionMapper.queryLatestVersion(flowKey);
    }

    @Override
    public FlowVersionAO getFlowVersionInfo(Long flowVersionId) {
        FlowVersionPO flowVersionPo = flowVersionMapper.queryById(flowVersionId);
        FlowVersionAO flowVersionAo = IFlowVersionConverter.IMPL.poToAo(flowVersionPo);
        return flowVersionAo;
    }

    @Override
    public FlowVersionInfoView queryFlowVersionInfoByKey(String flowKey, String flowVersion) {
        FlowVersionQueryVO flowVersionQueryVo = new FlowVersionQueryVO();
        flowVersionQueryVo.setFlowKey(flowKey);
        flowVersionQueryVo.setFlowVersion(flowVersion);
        FlowVersionInfoView flowVersionInfoView = flowVersionMapper.queryFlowVersionInfoByKey(flowVersionQueryVo);
        return flowVersionInfoView;
    }

    @Override
    public List<FlowVersionView> queryFlowVersionList(FlowVersionQueryVO flowVersionQueryVO) {
        List<FlowVersionView> flowVersionViewList = flowVersionMapper.queryFlowVersionList(flowVersionQueryVO);
        return flowVersionViewList;
    }
}
