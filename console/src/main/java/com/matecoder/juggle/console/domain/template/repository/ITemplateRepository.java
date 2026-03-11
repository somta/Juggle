package com.matecoder.juggle.console.domain.template.repository;

import com.matecoder.core.protocol.ResponsePaginationDataResult;
import com.matecoder.juggle.console.domain.template.vo.TemplateMarketClassifyVO;
import com.matecoder.juggle.console.domain.template.vo.TemplateMarketInfoVO;
import com.matecoder.juggle.console.domain.template.vo.TemplateMarketVO;

import java.util.List;

public interface ITemplateRepository {
    List<TemplateMarketClassifyVO> queryTemplateMarketClassifyList();

    ResponsePaginationDataResult<TemplateMarketVO> queryTemplateMarketList(Integer pageNum, Integer pageSize, String templateName, Long templateClassifyId, Integer priceStatus);

    TemplateMarketInfoVO queryTemplateMarketInfo(Long templateId,String bill);

    List<TemplateMarketVO> getRecommendTemplateList(Long templateId);
}
