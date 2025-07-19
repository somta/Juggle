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

import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.flow.flowinfo.FlowInfoAO;
import net.somta.juggle.console.domain.flow.flowinfo.repository.IFlowInfoRepository;
import net.somta.juggle.console.domain.flow.flowinfo.vo.FlowInfoQueryVO;
import net.somta.juggle.console.domain.flow.flowinfo.vo.FlowInfoVO;
import net.somta.juggle.console.domain.flow.version.enums.FlowVersionStatusEnum;
import net.somta.juggle.console.domain.flow.version.repository.IFlowVersionRepository;
import net.somta.juggle.console.infrastructure.converter.flow.IFlowInfoConverter;
import net.somta.juggle.console.infrastructure.converter.flow.IFlowVersionConverter;
import net.somta.juggle.console.infrastructure.mapper.flow.FlowInfoMapper;
import net.somta.juggle.console.infrastructure.mapper.flow.FlowVersionMapper;
import net.somta.juggle.console.infrastructure.po.flow.FlowInfoPO;
import net.somta.juggle.console.infrastructure.po.flow.FlowVersionPO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author husong
 * @since 1.0.0
 */
@Repository
public class FlowInfoRepositoryImpl implements IFlowInfoRepository {

    private final FlowInfoMapper flowInfoMapper;
    private final FlowVersionMapper flowVersionMapper;
    private final IFlowVersionRepository flowVersionRepository;

    public FlowInfoRepositoryImpl(FlowInfoMapper flowInfoMapper, FlowVersionMapper flowVersionMapper, IFlowVersionRepository flowVersionRepository) {
        this.flowInfoMapper = flowInfoMapper;
        this.flowVersionMapper = flowVersionMapper;
        this.flowVersionRepository = flowVersionRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteFlowInfoAndFlowVersion(Long flowId) {
        FlowInfoPO flowInfoPo = new FlowInfoPO();
        flowInfoPo.setId(flowId);
        flowInfoPo.setDeleted(1);
        flowInfoMapper.update(flowInfoPo);
        flowVersionMapper.deleteFlowVersionByFlowId(flowId);
        return true;
    }

    @Override
    public FlowInfoAO queryFlowInfo(Long flowInfoId) {
        FlowInfoPO flowInfoPo = flowInfoMapper.queryById(flowInfoId);
        FlowInfoAO flowInfoAo = IFlowInfoConverter.IMPL.poToAo(flowInfoPo);
        return flowInfoAo;
    }

    @Override
    public List<FlowInfoVO> queryFlowInfoList(FlowInfoQueryVO flowInfoQueryVO) {
        List<FlowInfoPO> flowInfoList = flowInfoMapper.queryByList(flowInfoQueryVO);
        List<FlowInfoVO> flowInfoVOList = IFlowInfoConverter.IMPL.poListToVoList(flowInfoList);
        return flowInfoVOList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deployFlow(FlowInfoAO flowInfoAo) {
        Date currentDate = new Date();
        FlowInfoPO flowInfoPo = flowInfoMapper.queryFlowByFlowKey(flowInfoAo.getFlowKey());
        if(flowInfoPo == null){
            flowInfoPo = IFlowInfoConverter.IMPL.aoToPo(flowInfoAo);
            flowInfoPo.setCreatedAt(currentDate);
            flowInfoPo.setCreatedBy(IdentityContext.getIdentity().getUserId());
            flowInfoMapper.addFlowInfo(flowInfoPo);
        }

        FlowVersionPO flowVersionPo = IFlowVersionConverter.IMPL.aoToPo(flowInfoAo);
        flowVersionPo.setFlowId(flowInfoPo.getId());
        flowVersionPo.setFlowVersionStatus(FlowVersionStatusEnum.DISABLED.getCode());
        flowVersionPo.setCreatedAt(currentDate);
        flowVersionPo.setCreatedBy(IdentityContext.getIdentity().getUserId());
        flowVersionMapper.add(flowVersionPo);

        flowVersionRepository.invalidateFlowCache(flowInfoAo.getFlowKey(),flowInfoAo.getFlowVersion());
        return true;
    }
}
