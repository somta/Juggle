package net.somta.juggle.console.domain.suite.suiteinfo.repository;

import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
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
    void addSuite(SuiteEntity suiteEntity);

    void updateSuite(SuiteEntity suiteEntity);

    void deleteSuiteById(Long suiteId);

    List<SuiteVO> querySuiteList(SuiteQueryVO suiteQueryVO);

    SuiteVO querySuiteByCode(String suiteCode);

    List<SuiteVO> querySuiteMarketList();

    SuiteMarketVO querySuiteMarketInfo(Long suiteId);


}
