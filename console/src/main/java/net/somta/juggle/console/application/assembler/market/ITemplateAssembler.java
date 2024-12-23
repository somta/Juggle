package net.somta.juggle.console.application.assembler.market;

import net.somta.juggle.console.domain.template.vo.TemplateMarketClassifyVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketInfoVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketVO;
import net.somta.juggle.console.interfaces.dto.market.TemplateMarketClassifyDTO;
import net.somta.juggle.console.interfaces.dto.market.TemplateMarketDTO;
import net.somta.juggle.console.interfaces.dto.market.TemplateMarketInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 * @since 1.2.3
 */
@Mapper
public interface ITemplateAssembler {

    ITemplateAssembler IMPL = Mappers.getMapper(ITemplateAssembler.class);

    TemplateMarketInfoDTO voToDto(TemplateMarketInfoVO templateMarketInfoVo);

    List<TemplateMarketClassifyDTO> voTemplateMarketListToDtoList(List<TemplateMarketClassifyVO> templateMarketClassifyVoList);

    List<TemplateMarketDTO> voListToDtoList(List<TemplateMarketVO> result);


}
