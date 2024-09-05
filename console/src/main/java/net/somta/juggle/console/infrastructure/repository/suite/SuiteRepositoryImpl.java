package net.somta.juggle.console.infrastructure.repository.suite;

import net.somta.common.utils.MapUtil;
import net.somta.common.utils.httpclient.HttpClientUtil;
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
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
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

    public SuiteRepositoryImpl(SuiteMapper suiteMapper) {
        this.suiteMapper = suiteMapper;
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
        List<SuiteVO> list = new ArrayList<>();
       /* ResponseDataResult result = HttpClientUtil.doPost(JUGGLE_OPEN_DOMAIN+"/open/v1/suite/market/list");
        if(result.isSuccess()){
            System.out.println(result.getResult());
            list = (List)result.getResult();
        }else {
            logger.error("调用Juggle开放域名接口失败{}",result.getResult());
        }
        return list;*/
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(JUGGLE_OPEN_DOMAIN+"/open/v1/suite/market/list");
            httpPost.setHeader("Content-type", "application/json");
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if(response != null){
                    int statusCode = response.getCode();
                    HttpEntity responseEntity = response.getEntity();
                    if (statusCode >= HttpStatus.SC_MULTIPLE_CHOICES) {
                        logger.error(response.getReasonPhrase());
                    }
                    if (responseEntity != null) {
                        String resultStr = EntityUtils.toString(responseEntity, "UTF-8");
                        ResponseDataResult resultData = JsonSerializeHelper.deserialize(resultStr,ResponseDataResult.class);
                        if(resultData.isSuccess()){
                            String listStr = JsonSerializeHelper.serialize(resultData.getResult());
                            list = JsonSerializeHelper.deserialize(listStr,List.class,SuiteVO.class);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SuiteMarketVO querySuiteMarketInfo(Long suiteId,String bill) {
        SuiteMarketVO suiteMarketVo = null;
        Map<String,Object> param = new HashMap<>();
        param.put("suiteId",suiteId);
        if(StringUtils.isNotEmpty(bill)){
            param.put("bill",bill);
        }
        ResponseDataResult result = HttpClientUtil.doPost(JUGGLE_OPEN_DOMAIN+"/open/v1/suite/market/info",JsonSerializeHelper.serialize(param));
        if(result.isSuccess()){
            suiteMarketVo = new SuiteMarketVO();
            ResponseDataResult resultData = JsonSerializeHelper.deserialize(String.valueOf(result.getResult()),ResponseDataResult.class);
            SuiteMarketInfoVO suiteMarketInfoVo = JsonSerializeHelper.deserialize(JsonSerializeHelper.serialize(resultData.getResult()), SuiteMarketInfoVO.class);
            suiteMarketVo = ISuiteConverter.IMPL.voToVo(suiteMarketInfoVo);
        }
        return suiteMarketVo;
    }
}
