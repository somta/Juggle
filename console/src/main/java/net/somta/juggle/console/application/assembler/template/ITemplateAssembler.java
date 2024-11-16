package net.somta.juggle.console.application.assembler.template;

import net.somta.juggle.console.domain.system.datasource.DataSourceAO;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceQueryVO;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketClassifyVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketInfoVO;
import net.somta.juggle.console.domain.template.vo.TemplateMarketVO;
import net.somta.juggle.console.interfaces.dto.system.DataSourceDTO;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketClassifyDTO;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketDTO;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketInfoDTO;
import net.somta.juggle.console.interfaces.param.system.DataSourceAddParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceQueryParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceUpdateParam;
import net.somta.juggle.core.model.DataSource;
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
