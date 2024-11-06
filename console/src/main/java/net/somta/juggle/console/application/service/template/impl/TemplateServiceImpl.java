package net.somta.juggle.console.application.service.template.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.exception.BizException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.assembler.template.ITemplateAssembler;
import net.somta.juggle.console.application.service.flow.IFlowDefinitionService;
import net.somta.juggle.console.application.service.template.ITemplateService;
import net.somta.juggle.console.domain.flow.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
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
import org.apache.commons.lang3.StringUtils;
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

    private final IFlowDefinitionService flowDefinitionService;

    public TemplateServiceImpl(ITemplateRepository templateRepository, ISuiteRepository suiteRepository, IFlowDefinitionService flowDefinitionService) {
        this.templateRepository = templateRepository;
        this.suiteRepository = suiteRepository;
        this.flowDefinitionService = flowDefinitionService;
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
        FlowDefinitionAO flowDefinitionAo = new FlowDefinitionAO();
        flowDefinitionAo.setFlowName(templateMarketInfoVo.getTemplateName());
        flowDefinitionAo.setFlowType(templateMarketInfoVo.getFlowType());
        flowDefinitionAo.setFlowKey(flowDefinitionAo.generateFlowKey());
        flowDefinitionAo.setFlowContent(templateMarketInfoVo.getFlowContent());
        flowDefinitionAo.setRemark(templateMarketInfoVo.getTemplateRemark());

        ParameterEntity parameterEntity = new ParameterEntity();
        if(StringUtils.isNotEmpty(templateMarketInfoVo.getFlowInputParams())){
            List<InputParameterVO> flowInputParamList = null;
            try {
                flowInputParamList = JsonSerializeHelper.deserialize(templateMarketInfoVo.getFlowInputParams(), List.class, InputParameterVO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            parameterEntity.setInputParameterList(flowInputParamList);
        }
        if(StringUtils.isNotEmpty(templateMarketInfoVo.getFlowOutputParams())){
            List<OutputParameterVO> flowOutputParamList = null;
            try {
                flowOutputParamList = JsonSerializeHelper.deserialize(templateMarketInfoVo.getFlowOutputParams(), List.class, OutputParameterVO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            parameterEntity.setOutputParameterList(flowOutputParamList);
        }
        flowDefinitionAo.setParameterEntity(parameterEntity);

        if(StringUtils.isNotBlank(templateMarketInfoVo.getFlowVariables())){
            try {
                List<VariableInfoVO> variableInfoList = JsonSerializeHelper.deserialize(templateMarketInfoVo.getFlowVariables(),List.class,VariableInfoVO.class);
                flowDefinitionAo.setVariableInfoList(variableInfoList);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        flowDefinitionService.createFlowDefinitionByTemplate(flowDefinitionAo);
        return true;
    }

    @Override
    public List<TemplateMarketDTO> getRecommendTemplateList(Long templateId) {
        List<TemplateMarketVO> templateMarketVoList = templateRepository.getRecommendTemplateList(templateId);
        return ITemplateAssembler.IMPL.voListToDtoList(templateMarketVoList);
    }
}
