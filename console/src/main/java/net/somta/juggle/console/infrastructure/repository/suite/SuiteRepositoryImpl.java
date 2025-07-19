package net.somta.juggle.console.infrastructure.repository.suite;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.configuration.JuggleProperties;
import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import net.somta.juggle.console.domain.suite.suiteinfo.repository.ISuiteRepository;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.*;
import net.somta.juggle.console.infrastructure.converter.suite.ISuiteConverter;
import net.somta.juggle.console.infrastructure.mapper.suite.SuiteMapper;
import net.somta.juggle.console.infrastructure.po.suite.SuitePO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author husong
 * @since 1.1.1
 */
@Repository
public class SuiteRepositoryImpl implements ISuiteRepository {
    private final static Logger logger = LoggerFactory.getLogger(SuiteRepositoryImpl.class);

    private final SuiteMapper suiteMapper;
    private final RestTemplate restTemplate;

    private final JuggleProperties juggleProperties;

    public SuiteRepositoryImpl(SuiteMapper suiteMapper, RestTemplate restTemplate, JuggleProperties juggleProperties) {
        this.suiteMapper = suiteMapper;
        this.restTemplate = restTemplate;
        this.juggleProperties = juggleProperties;
    }

    @Override
    public Long addSuite(SuiteEntity suiteEntity) {
        SuitePO suitePo = ISuiteConverter.IMPL.entityToPo(suiteEntity);
        suitePo.setSuiteVersion(suiteEntity.getSuiteVersion());
        suitePo.setSuiteFlag(suiteEntity.getSuiteFlag());
        suitePo.setCreatedAt(new Date());
        suitePo.setCreatedBy(IdentityContext.getIdentity().getUserId());
        suiteMapper.addSuite(suitePo);
        return suitePo.getId();
    }

    @Override
    public void updateSuite(SuiteEntity suiteEntity) {
        SuitePO suitePo = ISuiteConverter.IMPL.entityToPo(suiteEntity);
        suitePo.setUpdatedAt(new Date());
        suitePo.setCreatedBy(IdentityContext.getIdentity().getUserId());
        suiteMapper.update(suitePo);
    }

    @Override
    public void deleteSuiteById(Long suiteId) {
        suiteMapper.deleteById(suiteId);
    }

    @Override
    public SuiteVO querySuiteById(Long suiteId) {
        SuitePO suitePo =suiteMapper.queryById(suiteId);
        return ISuiteConverter.IMPL.poToVo(suitePo);
    }

    @Override
    public List<SuiteVO> querySuiteList(SuiteQueryVO suiteQueryVO) {
        List<SuitePO> suitePoList = suiteMapper.queryByList(suiteQueryVO);
        return ISuiteConverter.IMPL.poListToVoList(suitePoList);
    }

    @Override
    public SuiteVO querySuiteByCode(String suiteCode) {
        SuitePO suitePo = suiteMapper.querySuiteByCode(suiteCode);
        SuiteVO suiteVo = ISuiteConverter.IMPL.poToVo(suitePo);
        return suiteVo;
    }

    @Override
    public List<SuiteVO> queryExistSuiteByCodes(List<String> suiteCodes) {
        return suiteMapper.queryExistSuiteByCodes(suiteCodes);
    }

    @Override
    public List<SuiteMarketClassifyVO> querySuiteMarketClassifyList() {
        List<SuiteMarketClassifyVO> suiteMarketClassifyList = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);
        ResponseEntity<ResponseDataResult<List<SuiteMarketClassifyVO>>> response = restTemplate.exchange(
                juggleProperties.getOpenServerAddr()+"/open/v1/suite/market/classify/list",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponseDataResult<List<SuiteMarketClassifyVO>>>() {});
        if(response.getStatusCode() == HttpStatus.OK){
            suiteMarketClassifyList = response.getBody().getResult();
        }
        return suiteMarketClassifyList;
    }

    @Override
    public ResponsePaginationDataResult<SuiteVO> querySuiteMarketList(Integer pageNum,Integer pageSize,String suiteName,Long suiteClassifyId, Integer priceStatus) {
        ResponsePaginationDataResult<SuiteVO> result = new ResponsePaginationDataResult<>();
        Map<String,Object> param = new HashMap<>();
        param.put("pageNum",pageNum);
        param.put("pageSize",pageSize);
        if(StringUtils.isNotEmpty(suiteName)){
            param.put("suiteName",suiteName);
        }
        if(suiteClassifyId != null){
            param.put("suiteClassifyId",suiteClassifyId);
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
        ResponseEntity<ResponsePaginationDataResult<SuiteVO>> response = restTemplate.exchange(
                juggleProperties.getOpenServerAddr()+"/open/v1/suite/market/list",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponsePaginationDataResult<SuiteVO>>() {});
        if(response.getStatusCode() == HttpStatus.OK){
            result =  response.getBody();
        }
        return result;
    }

    @Override
    public SuiteMarketVO querySuiteMarketInfo(Long suiteId,String bill) {
        SuiteMarketVO suiteMarketVo = null;
        Map<String,Object> param = new HashMap<>();
        param.put("suiteId",suiteId);
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

        ResponseEntity<ResponseDataResult<SuiteMarketVO>> response = restTemplate.exchange(
                juggleProperties.getOpenServerAddr()+"/open/v1/suite/market/info",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponseDataResult<SuiteMarketVO>>() {});
        if(response.getStatusCode() == org.springframework.http.HttpStatus.OK){
            suiteMarketVo = response.getBody().getResult();
        }
        return suiteMarketVo;
    }

}
