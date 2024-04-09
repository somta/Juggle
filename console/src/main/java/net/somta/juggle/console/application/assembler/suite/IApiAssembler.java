package net.somta.juggle.console.application.assembler.suite;

import net.somta.juggle.console.domain.suite.api.ApiAO;
import net.somta.juggle.console.domain.suite.api.vo.ApiVO;
import net.somta.juggle.console.interfaces.dto.suite.ApiDTO;
import net.somta.juggle.console.interfaces.dto.suite.ApiInfoDTO;
import net.somta.juggle.console.interfaces.param.suite.ApiAddParam;
import net.somta.juggle.console.interfaces.param.suite.ApiUpdateParam;
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
