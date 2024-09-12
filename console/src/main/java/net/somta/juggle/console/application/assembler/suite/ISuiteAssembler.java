package net.somta.juggle.console.application.assembler.suite;

import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketClassifyVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.interfaces.dto.suite.SuiteDTO;
import net.somta.juggle.console.interfaces.dto.suite.SuiteMarketClassifyDTO;
import net.somta.juggle.console.interfaces.dto.suite.SuiteMarketDTO;
import net.somta.juggle.console.interfaces.dto.suite.SuiteMarketInfoDTO;
import net.somta.juggle.console.interfaces.param.suite.SuiteAddParam;
import net.somta.juggle.console.interfaces.param.suite.SuiteUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface ISuiteAssembler {
    ISuiteAssembler IMPL = Mappers.getMapper(ISuiteAssembler.class);

    SuiteEntity paramToEntity(SuiteAddParam suiteAddParam);

    SuiteEntity paramToEntity(SuiteUpdateParam suiteUpdateParam);

    List<SuiteDTO> voListToDtoList(List<SuiteVO> suiteVoList);

    SuiteMarketInfoDTO voToDto(SuiteMarketVO suiteMarketVo);

    List<SuiteMarketClassifyDTO> voSuiteMarketListToDtoList(List<SuiteMarketClassifyVO> suiteVoList);

}
