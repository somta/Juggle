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
import net.somta.core.exception.BizException;
import net.somta.juggle.console.application.assembler.flow.IFlowInfoAssembler;
import net.somta.juggle.console.domain.flow.flowinfo.FlowInfoAO;
import net.somta.juggle.console.domain.flow.flowinfo.repository.IFlowInfoRepository;
import net.somta.juggle.console.application.service.flow.IFlowInfoService;
import net.somta.juggle.console.domain.flow.flowinfo.vo.FlowInfoQueryVO;
import net.somta.juggle.console.domain.flow.flowinfo.vo.FlowInfoVO;
import net.somta.juggle.console.domain.flow.version.enums.FlowVersionStatusEnum;
import net.somta.juggle.console.domain.flow.version.repository.IFlowVersionRepository;
import net.somta.juggle.console.domain.flow.version.view.FlowVersionView;
import net.somta.juggle.console.domain.flow.version.vo.FlowVersionQueryVO;
import net.somta.juggle.console.interfaces.dto.flow.FlowInfoDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowInfoPageParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.somta.juggle.console.domain.flow.version.enums.FlowVersionErrorEnum.ENABLE_FLOW_NOT_DELETE;

/**
 * @author husong
 * @since 1.0.0
 */
@Service
public class FlowInfoInfoServiceImpl implements IFlowInfoService {

    private final IFlowInfoRepository flowInfoRepository;
    private final IFlowVersionRepository flowVersionRepository;

    public FlowInfoInfoServiceImpl(IFlowInfoRepository flowInfoRepository, IFlowVersionRepository flowVersionRepository) {
        this.flowInfoRepository = flowInfoRepository;
        this.flowVersionRepository = flowVersionRepository;
    }

    @Override
    public Boolean deleteFlowInfo(Long flowId) {
        FlowInfoAO flowInfoAo = flowInfoRepository.queryFlowInfo(flowId);
        FlowVersionQueryVO flowVersionQueryVO = new FlowVersionQueryVO();
        flowVersionQueryVO.setFlowId(flowInfoAo.getId());
        flowVersionQueryVO.setFlowVersionStatus(FlowVersionStatusEnum.ENABLE.getCode());
        List<FlowVersionView> flowVersionViewList = flowVersionRepository.queryFlowVersionList(flowVersionQueryVO);
        if(CollectionUtils.isNotEmpty(flowVersionViewList)){
            throw new BizException(ENABLE_FLOW_NOT_DELETE);
        }
        return flowInfoRepository.deleteFlowInfoAndFlowVersion(flowId);
    }

    @Override
    public PageInfo<FlowInfoDTO> getFlowInfoPageList(FlowInfoPageParam flowInfoPageParam) {
        FlowInfoQueryVO flowInfoQueryVO = IFlowInfoAssembler.IMPL.paramToVo(flowInfoPageParam);
        Page<FlowInfoDTO> page = PageHelper.startPage(flowInfoPageParam.getPageNum(), flowInfoPageParam.getPageSize());
        List<FlowInfoVO> flowInfoList = flowInfoRepository.queryFlowInfoList(flowInfoQueryVO);
        List<FlowInfoDTO> flowInfoDTOList = IFlowInfoAssembler.IMPL.voListToDtoList(flowInfoList);
        PageInfo<FlowInfoDTO> pageInfo = new PageInfo<>(flowInfoDTOList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

}
