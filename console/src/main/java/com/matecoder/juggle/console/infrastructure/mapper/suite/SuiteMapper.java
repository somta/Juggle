package com.matecoder.juggle.console.infrastructure.mapper.suite;

import com.matecoder.core.base.IBaseMapper;
import com.matecoder.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import com.matecoder.juggle.console.infrastructure.po.suite.SuitePO;

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
