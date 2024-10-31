package net.somta.juggle.console.application.service.template;

import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketClassifyDTO;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketDTO;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketInfoDTO;
import net.somta.juggle.console.interfaces.param.template.TemplateMarketParam;
import net.somta.juggle.console.interfaces.param.template.TemplateMarketQueryParam;

import java.util.List;

/**
 * @author husong
 * @since 1.2.3
 */
public interface ITemplateService {
    List<TemplateMarketClassifyDTO> getTemplateMarketClassifyList();

    ResponsePaginationDataResult<TemplateMarketDTO> getTemplateMarketList(TemplateMarketQueryParam templateMarketQueryParam);

    TemplateMarketInfoDTO getTemplateMarketInfo(Long suiteId);

    Boolean useTemplateMarket(TemplateMarketParam templateMarketParam);
}
