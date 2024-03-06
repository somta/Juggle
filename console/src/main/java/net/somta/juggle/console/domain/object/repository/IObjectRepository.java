package net.somta.juggle.console.domain.object.repository;

import net.somta.juggle.console.domain.object.ObjectAO;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.interfaces.param.ObjectQueryParam;

import java.util.List;

/**
 * @author husong
 */
public interface IObjectRepository {
    Boolean addObject(ObjectAO objectAo);

    Boolean deleteObjectById(Long objId);

    Boolean updateObject(ObjectAO objectAo);

    ObjectAO queryObject(Long objId);

    ObjectAO queryObjectInfoByKey(String objectKey);

    List<ObjectVO> queryObjectInfoList();

    List<ObjectVO> queryObjectPageList(ObjectQueryParam objectQueryParam);



}
