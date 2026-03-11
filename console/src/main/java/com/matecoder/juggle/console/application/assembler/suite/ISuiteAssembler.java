package com.matecoder.juggle.console.application.assembler.suite;

import com.matecoder.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import com.matecoder.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketClassifyVO;
import com.matecoder.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketVO;
import com.matecoder.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import com.matecoder.juggle.console.interfaces.dto.suite.SuiteDTO;
import com.matecoder.juggle.console.interfaces.dto.suite.SuiteMarketClassifyDTO;
import com.matecoder.juggle.console.interfaces.dto.suite.SuiteMarketDTO;
import com.matecoder.juggle.console.interfaces.dto.suite.SuiteMarketInfoDTO;
import com.matecoder.juggle.console.interfaces.param.suite.SuiteAddParam;
import com.matecoder.juggle.console.interfaces.param.suite.SuiteUpdateParam;
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
