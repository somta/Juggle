package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.obj.ObjAO;
import net.somta.juggle.console.domain.obj.vo.ObjVO;
import net.somta.juggle.console.interfaces.dto.ObjDTO;
import net.somta.juggle.console.interfaces.dto.ObjInfoDTO;
import net.somta.juggle.console.interfaces.param.ObjAddParam;
import net.somta.juggle.console.interfaces.param.ObjUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IObjAssembler {
    IObjAssembler IMPL = Mappers.getMapper(IObjAssembler.class);

    ObjAO paramToAo(ObjAddParam objAddParam);

    ObjAO paramToAo(ObjUpdateParam objUpdateParam);

    @Mapping(target = "props", expression = "java(objAo.getPropertyList())")
    ObjInfoDTO aoToDto(ObjAO objAo);

    List<ObjDTO> voListToDtoList(List<ObjVO> objList);
}
