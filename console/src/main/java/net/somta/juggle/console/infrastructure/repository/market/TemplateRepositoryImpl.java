package net.somta.juggle.console.infrastructure.repository.market;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.configuration.JuggleProperties;
import net.somta.juggle.console.domain.template.repository.ITemplateRepository;
import net.somta.juggle.console.domain.template.vo.TemplateMarketClassifyVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketInfoVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author husong
 * @since 1.2.3
 */
@Repository
public class TemplateRepositoryImpl implements ITemplateRepository {

    private final RestTemplate restTemplate;

    private final JuggleProperties juggleProperties;

    public TemplateRepositoryImpl(RestTemplate restTemplate, JuggleProperties juggleProperties) {
        this.restTemplate = restTemplate;
        this.juggleProperties = juggleProperties;
    }

    @Override
    public List<TemplateMarketClassifyVO> queryTemplateMarketClassifyList() {
        List<TemplateMarketClassifyVO> templateMarketClassifyList = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);
        ResponseEntity<ResponseDataResult<List<TemplateMarketClassifyVO>>> response = restTemplate.exchange(
                juggleProperties.getOpenServerAddr()+"/open/v1/template/market/classify/list",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponseDataResult<List<TemplateMarketClassifyVO>>>() {});
        if(response.getStatusCode() == HttpStatus.OK){
            templateMarketClassifyList = response.getBody().getResult();
        }
        return templateMarketClassifyList;
    }

    @Override
    public ResponsePaginationDataResult<TemplateMarketVO> queryTemplateMarketList(Integer pageNum, Integer pageSize, String templateName, Long templateClassifyId, Integer priceStatus) {
        ResponsePaginationDataResult<TemplateMarketVO> result = new ResponsePaginationDataResult<>();
        Map<String,Object> param = new HashMap<>();
        param.put("pageNum",pageNum);
        param.put("pageSize",pageSize);
        if(StringUtils.isNotEmpty(templateName)){
            param.put("templateName",templateName);
        }
        if(templateClassifyId != null){
            param.put("templateClassifyId",templateClassifyId);
        }
        if(priceStatus != null){
            param.put("priceStatus",priceStatus);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = null;
        try {
            entity = new HttpEntity<>(JsonSerializeHelper.serialize(param),headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<ResponsePaginationDataResult<TemplateMarketVO>> response = restTemplate.exchange(
                juggleProperties.getOpenServerAddr()+"/open/v1/template/market/list",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponsePaginationDataResult<TemplateMarketVO>>() {});
        if(response.getStatusCode() == HttpStatus.OK){
            result =  response.getBody();
        }
        return result;
    }

    @Override
    public TemplateMarketInfoVO queryTemplateMarketInfo(Long templateId,String bill) {
        TemplateMarketInfoVO templateMarketVo = null;
        Map<String,Object> param = new HashMap<>();
        param.put("templateId",templateId);
        if(StringUtils.isNotEmpty(bill)){
            param.put("bill",bill);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = null;
        try {
            entity = new HttpEntity<>(JsonSerializeHelper.serialize(param),headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ResponseEntity<ResponseDataResult<TemplateMarketInfoVO>> response = restTemplate.exchange(
                juggleProperties.getOpenServerAddr()+"/open/v1/template/market/info",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponseDataResult<TemplateMarketInfoVO>>() {});
        if(response.getStatusCode() == HttpStatus.OK){
            templateMarketVo = response.getBody().getResult();
        }
        return templateMarketVo;
    }

    @Override
    public List<TemplateMarketVO> getRecommendTemplateList(Long templateId) {
        List<TemplateMarketVO> templateMarketVoList = new ArrayList<>();
        Map<String,Object> param = new HashMap<>();
        param.put("templateId",templateId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = null;
        try {
            entity = new HttpEntity<>(JsonSerializeHelper.serialize(param),headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ResponseEntity<ResponseDataResult<List<TemplateMarketVO>>> response = restTemplate.exchange(
                juggleProperties.getOpenServerAddr()+"/open/v1/template/market/recommend",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponseDataResult<List<TemplateMarketVO>>>() {});
        if(response.getStatusCode() == HttpStatus.OK){
            templateMarketVoList = response.getBody().getResult();
        }
        return templateMarketVoList;
    }
}
