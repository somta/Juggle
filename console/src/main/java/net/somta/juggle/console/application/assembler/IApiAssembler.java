package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.api.ApiAO;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IApiAssembler {
    IApiAssembler IMPL = Mappers.getMapper(IApiAssembler.class);

    @Mapping(target = "apiInputParams", expression = "java(apiAO.getParameterEntity().getInputParameter())")
    @Mapping(target = "apiOutputParams", expression = "java(apiAO.getParameterEntity().getOutputParameter())")
    ApiInfoDTO aoToDto(ApiAO apiAO);
}
