package net.somta.juggle.console.infrastructure.mapper.suite;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.infrastructure.po.suite.SuitePO;

import java.util.List;

/**
 * @author husong
 * @since 1.1.1
 */
public interface SuiteMapper extends IBaseMapper {

    int addSuite(SuitePO suitePo);

    SuitePO querySuiteByCode(String suiteCode);

    List<SuiteVO> queryExistSuiteByCodes(List<String> suiteCodes);
}
