package net.somta.juggle.console.infrastructure.repository.flow;

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
    public List<FlowTemplateInfoVO> queryFlowTemplateList(FlowTemplateQueryVO flowTemplateQueryVO) {
        List<FlowTemplatePO> templateList = flowTemplateMapper.queryByList(flowTemplateQueryVO);
        return IFlowTemplateConverter.IMPL.poListToVoList(templateList);
    }
}
