package net.somta.juggle.console.infrastructure.converter.suite;

import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.infrastructure.po.suite.SuitePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface ISuiteConverter {
    ISuiteConverter IMPL = Mappers.getMapper(ISuiteConverter.class);

    SuitePO entityToPo(SuiteEntity suiteEntity);

    List<SuiteVO> poListToVoList(List<SuitePO> suitePoList);

    SuiteVO poToVo(SuitePO suitePo);
}
