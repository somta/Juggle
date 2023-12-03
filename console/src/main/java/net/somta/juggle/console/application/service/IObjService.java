package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.dto.ObjInfoDTO;
import net.somta.juggle.console.interfaces.param.ObjAddParam;
import net.somta.juggle.console.interfaces.param.ObjQueryParam;
import net.somta.juggle.console.interfaces.param.ObjUpdateParam;

import java.util.List;

/**
 * @author husong
 */
public interface IObjService {
    Boolean addObj(ObjAddParam objAddParam);

    Boolean deleteObj(Long objId);

    Boolean updateObj(ObjUpdateParam objUpdateParam);

    ObjInfoDTO getObjInfo(Long objId);

    List<ObjInfoDTO> getObjList();

    PageInfo getObjPageList(ObjQueryParam objQueryParam);
}
