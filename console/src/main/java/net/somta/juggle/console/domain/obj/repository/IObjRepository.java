package net.somta.juggle.console.domain.obj.repository;

import net.somta.juggle.console.domain.obj.ObjAO;
import net.somta.juggle.console.domain.obj.vo.ObjVO;
import net.somta.juggle.console.interfaces.param.ObjQueryParam;

import java.util.List;

/**
 * @author husong
 */
public interface IObjRepository {
    Boolean addObj(ObjAO objAO);

    Boolean deleteObjById(Long objId);

    Boolean updateObj(ObjAO objAO);

    ObjAO queryApi(Long objId);

    List<ObjVO> queryObjPageList(ObjQueryParam objQueryParam);
}
