package net.somta.juggle.console.application.service.template.impl;

import net.somta.core.exception.BizException;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.assembler.template.ITemplateAssembler;
import net.somta.juggle.console.application.service.template.ITemplateService;
import net.somta.juggle.console.domain.suite.suiteinfo.repository.ISuiteRepository;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.domain.template.repository.ITemplateRepository;
import net.somta.juggle.console.domain.template.vo.TemplateMarketClassifyVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketInfoVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketVO;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketClassifyDTO;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketDTO;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketInfoDTO;
import net.somta.juggle.console.interfaces.param.template.TemplateMarketParam;
import net.somta.juggle.console.interfaces.param.template.TemplateMarketQueryParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static net.somta.juggle.console.domain.template.enums.TemplateErrorEnum.TEMPLATE_NOT_EXIST_ERROR;

/**
 * @author husong
 * @since 1.2.3
 */
@Service
public class TemplateServiceImpl implements ITemplateService {

    private final ITemplateRepository templateRepository;
    private final ISuiteRepository suiteRepository;

    public TemplateServiceImpl(ITemplateRepository templateRepository, ISuiteRepository suiteRepository) {
        this.templateRepository = templateRepository;
        this.suiteRepository = suiteRepository;
    }

    @Override
    public List<TemplateMarketClassifyDTO> getTemplateMarketClassifyList() {
        List<TemplateMarketClassifyVO> templateMarketClassifyVoList = templateRepository.queryTemplateMarketClassifyList();
        List<TemplateMarketClassifyDTO> classifyDtoList = ITemplateAssembler.IMPL.voTemplateMarketListToDtoList(templateMarketClassifyVoList);
        return classifyDtoList;
    }

    @Override
    public ResponsePaginationDataResult<TemplateMarketDTO> getTemplateMarketList(TemplateMarketQueryParam templateMarketQueryParam) {
        ResponsePaginationDataResult<TemplateMarketVO> result = templateRepository.queryTemplateMarketList(templateMarketQueryParam.getPageNum(),templateMarketQueryParam.getPageSize(),templateMarketQueryParam.getTemplateName(),templateMarketQueryParam.getTemplateClassifyId());
        List<TemplateMarketDTO> templateList = ITemplateAssembler.IMPL.voListToDtoList(result.getResult());
        return ResponsePaginationDataResult.setPaginationDataResult(result.getTotal(),templateList);
    }

    @Override
    public TemplateMarketInfoDTO getTemplateMarketInfo(Long templateId) {
        TemplateMarketInfoVO templateMarketInfoVo = templateRepository.queryTemplateMarketInfo(templateId,null);
        TemplateMarketInfoDTO templateMarketInfoDto = ITemplateAssembler.IMPL.voToDto(templateMarketInfoVo);
        List<String> suiteCodes = templateMarketInfoVo.getSuiteList().stream().map(suiteVO -> suiteVO.getSuiteCode()).collect(Collectors.toList());
        List<SuiteVO> isExistSuiteList = suiteRepository.queryExistSuiteByCodes(suiteCodes);

        List<SuiteVO> noBuySuiteList = templateMarketInfoVo.getSuiteList().stream()
                .filter(templateSuite -> isExistSuiteList.stream().noneMatch(localSuite -> templateSuite.getSuiteCode().equals(localSuite.getSuiteCode())))
                .collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(noBuySuiteList)){
            templateMarketInfoDto.setNoBuySuiteList(noBuySuiteList);
        }
        return templateMarketInfoDto;
    }

    @Override
    public Boolean useTemplateMarket(TemplateMarketParam templateMarketParam) {
        TemplateMarketInfoVO templateMarketInfoVo = templateRepository.queryTemplateMarketInfo(templateMarketParam.getTemplateId(),templateMarketParam.getBill());
        if(templateMarketInfoVo == null){
            throw new BizException(TEMPLATE_NOT_EXIST_ERROR);
        }
        return true;
    }

    @Override
    public List<TemplateMarketDTO> getRecommendTemplateList(Long templateId) {
        List<TemplateMarketVO> templateMarketVoList = templateRepository.getRecommendTemplateList(templateId);
        return ITemplateAssembler.IMPL.voListToDtoList(templateMarketVoList);
    }
}
