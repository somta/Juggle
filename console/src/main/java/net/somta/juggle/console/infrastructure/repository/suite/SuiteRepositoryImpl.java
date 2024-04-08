package net.somta.juggle.console.infrastructure.repository.suite;

import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import net.somta.juggle.console.domain.suite.suiteinfo.repository.ISuiteRepository;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteQueryVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.infrastructure.converter.suite.ISuiteConverter;
import net.somta.juggle.console.infrastructure.mapper.suite.SuiteMapper;
import net.somta.juggle.console.infrastructure.po.suite.SuitePO;
import net.somta.juggle.console.interfaces.param.suite.SuiteQueryParam;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author husong
 * @since 1.1.1
 */
@Repository
public class SuiteRepositoryImpl implements ISuiteRepository {
    private final SuiteMapper suiteMapper;

    public SuiteRepositoryImpl(SuiteMapper suiteMapper) {
        this.suiteMapper = suiteMapper;
    }

    @Override
    public void addSuite(SuiteEntity suiteEntity) {
        SuitePO suitePo = ISuiteConverter.IMPL.entityToPo(suiteEntity);
        suitePo.setSuiteVersion("v1.0.0");
        suitePo.setSuiteFlag(1);
        suitePo.setCreatedAt(new Date());
        suitePo.setCreatedBy(IdentityContext.getIdentity().getUserId());
        suiteMapper.add(suitePo);
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
}
