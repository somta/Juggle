package net.somta.juggle.console.domain.template.repository;

import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketClassifyVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketInfoVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketVO;

import java.util.List;

public interface ITemplateRepository {
    List<TemplateMarketClassifyVO> queryTemplateMarketClassifyList();

    ResponsePaginationDataResult<TemplateMarketVO> queryTemplateMarketList(Integer pageNum, Integer pageSize, String templateName, Long templateClassifyId);

    TemplateMarketInfoVO queryTemplateMarketInfo(Long templateId,String bill);
}
