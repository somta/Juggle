package net.somta.juggle.console.domain.suite.suiteinfo.repository;

import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketClassifyVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteQueryVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.interfaces.param.suite.SuiteQueryParam;

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
