package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.dto.ObjectDTO;
import net.somta.juggle.console.interfaces.dto.ObjectInfoDTO;
import net.somta.juggle.console.interfaces.param.ObjectAddParam;
import net.somta.juggle.console.interfaces.param.ObjectQueryParam;
import net.somta.juggle.console.interfaces.param.ObjectUpdateParam;

import java.util.List;

/**
 * @author husong
 */
public interface IObjectService {

    /**
     * Add Object
     * @param objectAddParam Add object parameters
     * @return Boolean
     */
    Boolean addObject(ObjectAddParam objectAddParam);

    /**
     * Delete object based on ID
     * @param objId object id
     * @return Boolean
     */
    Boolean deleteObject(Long objId);

    /**
     * Update Object
     * @param objectUpdateParam Update object parameters
     * @return Boolean
     */
    Boolean updateObject(ObjectUpdateParam objectUpdateParam);

    /**
     * Query object information based on object ID
     * @param objId object id
     * @return Object info
     */
    ObjectInfoDTO getObjectInfo(Long objId);

    /**
     * Get object list
     * @return Object list
     */
    List<ObjectDTO> getObjectList();

    /**
     * Query Object Paging List
     * @param objectQueryParam Object pagination query parameters
     * @return Object Paging Information
     */
    PageInfo getObjectPageList(ObjectQueryParam objectQueryParam);

    Boolean releaseObject(Long objectId);
}
