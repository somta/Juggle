package net.somta.juggle.console.infrastructure.mapper.suite;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.suite.SuitePO;

/**
 * @author husong
 * @since 1.1.1
 */
public interface SuiteMapper extends IBaseMapper {

    SuitePO querySuiteByCode(String suiteCode);
}
