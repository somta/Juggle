package com.matecoder.juggle.console.application.assembler.suite;

import com.matecoder.juggle.console.domain.suite.api.ApiAO;
import com.matecoder.juggle.console.domain.suite.api.vo.ApiVO;
import com.matecoder.juggle.console.interfaces.dto.suite.ApiDTO;
import com.matecoder.juggle.console.interfaces.dto.suite.ApiInfoDTO;
import com.matecoder.juggle.console.interfaces.param.suite.ApiAddParam;
import com.matecoder.juggle.console.interfaces.param.suite.ApiUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IApiAssembler {
    IApiAssembler IMPL = Mappers.getMapper(IApiAssembler.class);


    ApiAO paramToAo(ApiAddParam apiAddParam);

    ApiAO paramToAo(ApiUpdateParam apiUpdateParam);

    @Mapping(target = "apiInputParams", expression = "java(apiAo.getParameterEntity().getInputParameterList())")
    @Mapping(target = "apiOutputParams", expression = "java(apiAo.getParameterEntity().getOutputParameterList())")
    ApiInfoDTO aoToDto(ApiAO apiAo);

    List<ApiDTO> voListToDtoList(List<ApiVO> apiList);
}
