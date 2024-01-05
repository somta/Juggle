package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.dto.ObjDTO;
import net.somta.juggle.console.interfaces.dto.ObjInfoDTO;
import net.somta.juggle.console.interfaces.param.ObjAddParam;
import net.somta.juggle.console.interfaces.param.ObjQueryParam;
import net.somta.juggle.console.interfaces.param.ObjUpdateParam;

import java.util.List;

/**
 * @author husong
 */
public interface IObjService {

    /**
     * Add Object
     * @param objAddParam Add object parameters
     * @return Boolean
     */
    Boolean addObj(ObjAddParam objAddParam);

    /**
     * Delete object based on ID
     * @param objId object id
     * @return Boolean
     */
    Boolean deleteObj(Long objId);

    /**
     * Update Object
     * @param objUpdateParam Update object parameters
     * @return Boolean
     */
    Boolean updateObj(ObjUpdateParam objUpdateParam);

    /**
     * Query object information based on object ID
     * @param objId object id
     * @return Object info
     */
    ObjInfoDTO getObjInfo(Long objId);

    /**
     * Get object list
     * @return Object list
     */
    List<ObjDTO> getObjList();

    /**
     * Query Object Paging List
     * @param objQueryParam Object pagination query parameters
     * @return Object Paging Information
     */
    PageInfo getObjPageList(ObjQueryParam objQueryParam);
}
