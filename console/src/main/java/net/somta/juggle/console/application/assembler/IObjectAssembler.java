package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.object.ObjectAO;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.interfaces.dto.ObjectDTO;
import net.somta.juggle.console.interfaces.dto.ObjectInfoDTO;
import net.somta.juggle.console.interfaces.param.ObjectAddParam;
import net.somta.juggle.console.interfaces.param.ObjectUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IObjectAssembler {
    IObjectAssembler IMPL = Mappers.getMapper(IObjectAssembler.class);

    @Mapping(target = "propertyList", expression = "java(objectAddParam.getProps())")
    ObjectAO paramToAo(ObjectAddParam objectAddParam);

    @Mapping(target = "propertyList", expression = "java(objectUpdateParam.getProps())")
    ObjectAO paramToAo(ObjectUpdateParam objectUpdateParam);

    @Mapping(target = "props", expression = "java(objectAo.getPropertyList())")
    ObjectInfoDTO aoToDto(ObjectAO objectAo);

    List<ObjectDTO> voListToDtoList(List<ObjectVO> objList);
}
