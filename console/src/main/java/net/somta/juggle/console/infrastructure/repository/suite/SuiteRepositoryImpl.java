package net.somta.juggle.console.infrastructure.repository.suite;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import net.somta.juggle.console.domain.suite.suiteinfo.repository.ISuiteRepository;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketInfoVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteQueryVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
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

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_OPEN_DOMAIN;

/**
 * @author husong
 * @since 1.1.1
 */
@Repository
public class SuiteRepositoryImpl implements ISuiteRepository {
    private final static Logger logger = LoggerFactory.getLogger(SuiteRepositoryImpl.class);

    private final SuiteMapper suiteMapper;
    private final RestTemplate restTemplate;

    public SuiteRepositoryImpl(SuiteMapper suiteMapper, RestTemplate restTemplate) {
        this.suiteMapper = suiteMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public Long addSuite(SuiteEntity suiteEntity) {
        SuitePO suitePo = ISuiteConverter.IMPL.entityToPo(suiteEntity);
        suitePo.setSuiteVersion("v1.0.0");
        suitePo.setSuiteFlag(1);
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
    public List<SuiteVO> querySuiteMarketList() {
        List<SuiteVO> suiteList = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);
        ResponseEntity<ResponseDataResult<List<SuiteVO>>> response = restTemplate.exchange(
                JUGGLE_OPEN_DOMAIN+"/open/v1/suite/market/list",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponseDataResult<List<SuiteVO>>>() {});
        if(response.getStatusCode() == org.springframework.http.HttpStatus.OK){
            suiteList = response.getBody().getResult();
        }
        return suiteList;
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
        HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(JsonSerializeHelper.serialize(param),headers);

        ResponseEntity<ResponseDataResult<SuiteMarketInfoVO>> response = restTemplate.exchange(
                JUGGLE_OPEN_DOMAIN+"/open/v1/suite/market/info",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponseDataResult<SuiteMarketInfoVO>>() {});
        if(response.getStatusCode() == org.springframework.http.HttpStatus.OK){
            SuiteMarketInfoVO suiteMarketInfoVo = response.getBody().getResult();
            suiteMarketVo = ISuiteConverter.IMPL.voToVo(suiteMarketInfoVo);
        }
        return suiteMarketVo;
    }
}
