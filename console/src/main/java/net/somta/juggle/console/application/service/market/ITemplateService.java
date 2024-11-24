package net.somta.juggle.console.application.service.market;

import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.interfaces.dto.market.TemplateMarketClassifyDTO;
import net.somta.juggle.console.interfaces.dto.market.TemplateMarketDTO;
import net.somta.juggle.console.interfaces.dto.market.TemplateMarketInfoDTO;
import net.somta.juggle.console.interfaces.param.market.TemplateMarketParam;
import net.somta.juggle.console.interfaces.param.market.TemplateMarketQueryParam;

import java.util.List;

/**
 * @author husong
 * @since 1.2.3
 */
public interface ITemplateService {
    List<TemplateMarketClassifyDTO> getTemplateMarketClassifyList();

    ResponsePaginationDataResult<TemplateMarketDTO> getTemplateMarketList(TemplateMarketQueryParam templateMarketQueryParam);

    TemplateMarketInfoDTO getTemplateMarketInfo(Long templateId);

    Boolean useTemplateMarket(TemplateMarketParam templateMarketParam);

    List<TemplateMarketDTO> getRecommendTemplateList(Long templateId);
}
