package com.matecoder.juggle.console.domain.object.repository;

import com.matecoder.juggle.console.domain.object.ObjectAO;
import com.matecoder.juggle.console.domain.object.vo.ObjectVO;
import com.matecoder.juggle.console.interfaces.param.ObjectQueryParam;

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
