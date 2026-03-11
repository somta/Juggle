package com.matecoder.juggle.console.domain.suite.suiteinfo.repository;

import com.matecoder.core.protocol.ResponsePaginationDataResult;
import com.matecoder.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import com.matecoder.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketClassifyVO;
import com.matecoder.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketVO;
import com.matecoder.juggle.console.domain.suite.suiteinfo.vo.SuiteQueryVO;
import com.matecoder.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import com.matecoder.juggle.console.interfaces.param.suite.SuiteQueryParam;

import java.util.List;

/**
 * @author husong
 * @since 1.1.1
 */
public interface ISuiteRepository {
    Long addSuite(SuiteEntity suiteEntity);

    void updateSuite(SuiteEntity suiteEntity);

    void deleteSuiteById(Long suiteId);

    SuiteVO querySuiteById(Long suiteId);

    List<SuiteVO> querySuiteList(SuiteQueryVO suiteQueryVO);

    SuiteVO querySuiteByCode(String suiteCode);

    List<SuiteMarketClassifyVO> querySuiteMarketClassifyList();

    ResponsePaginationDataResult<SuiteVO> querySuiteMarketList(Integer pageNum,Integer pageSize,String suiteName, Long suiteClassifyId, Integer priceStatus);

    SuiteMarketVO querySuiteMarketInfo(Long suiteId,String bill);

    List<SuiteVO> queryExistSuiteByCodes(List<String> suiteCodes);
}
