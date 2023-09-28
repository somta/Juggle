package net.somta.juggle.console.application.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IParameterAssembler {
    IParameterAssembler IMPL = Mappers.getMapper(IParameterAssembler.class);
}
