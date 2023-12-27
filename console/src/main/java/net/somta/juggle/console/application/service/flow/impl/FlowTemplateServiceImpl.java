package net.somta.juggle.console.application.service.flow.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.assembler.flow.IFlowDefinitionAssembler;
import net.somta.juggle.console.application.assembler.flow.IFlowTemplateAssembler;
import net.somta.juggle.console.application.service.flow.IFlowTemplateService;
import net.somta.juggle.console.domain.flow.definition.vo.FlowDefinitionInfoVO;
import net.somta.juggle.console.domain.flow.template.repository.IFlowTemplateRepository;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateInfoVO;
import net.somta.juggle.console.domain.flow.template.vo.FlowTemplateQueryVO;
import net.somta.juggle.console.interfaces.dto.flow.FlowTemplateInfoDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowTemplatePageParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 */
@Service
public class FlowTemplateServiceImpl implements IFlowTemplateService {

    private final IFlowTemplateRepository flowTemplateRepository;

    public FlowTemplateServiceImpl(IFlowTemplateRepository flowTemplateRepository) {
        this.flowTemplateRepository = flowTemplateRepository;
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
}
