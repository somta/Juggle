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
    public PageInfo getFlowInfoPageList(FlowInfoPageParam flowInfoPageParam) {
        FlowInfoQueryVO flowInfoQueryVO = IFlowInfoAssembler.IMPL.paramToVo(flowInfoPageParam);
        Page<FlowInfoDTO> page = PageHelper.startPage(flowInfoPageParam.getPageNum(), flowInfoPageParam.getPageSize());
        List<FlowInfoVO> flowInfoList = flowInfoRepository.queryFlowInfoList(flowInfoQueryVO);
        List<FlowInfoDTO> flowInfoDTOList = IFlowInfoAssembler.IMPL.voListToDtoList(flowInfoList);
        PageInfo pageInfo = new PageInfo(flowInfoDTOList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

}
