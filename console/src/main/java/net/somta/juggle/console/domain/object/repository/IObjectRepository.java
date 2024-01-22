package net.somta.juggle.console.domain.object.repository;

import net.somta.juggle.console.domain.object.ObjectAO;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.interfaces.param.ObjectQueryParam;

import java.util.List;

/**
 * @author husong
 */
public interface IObjectRepository {
    Boolean addObject(ObjectAO objectAO);

    Boolean deleteObjectById(Long objId);

    Boolean updateObject(ObjectAO objectAO);

    ObjectAO queryObject(Long objId);

    List<ObjectVO> queryObjectList();

    List<ObjectVO> queryObjectPageList(ObjectQueryParam objectQueryParam);


}
