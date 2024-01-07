package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import net.somta.juggle.console.interfaces.param.ApiAddParam;
import net.somta.juggle.console.interfaces.param.ApiQueryParam;
import net.somta.juggle.console.interfaces.param.ApiUpdateParam;

import java.util.List;

/**
 * @author Gavin
 */
public interface IApiService {

    /**
     * Add API interface
     * @param apiAddParam Add API interface parameters
     * @return Boolean
     */
    Boolean addApi(ApiAddParam apiAddParam);

    /**
     * Delete API interface
     * @param apiId api id
     * @return Boolean
     */
    Boolean deleteApi(Long apiId);

    /**
     * Update API interface information
     * @param apiUpdateParam Update API interface parameters
     * @return Boolean
     */
    Boolean updateApi(ApiUpdateParam apiUpdateParam);

    /**
     * Query API interface information
     * @param apiId api id
     * @return Interface information dto object
     */
    ApiInfoDTO getApiInfo(Long apiId);

    /**
     * Query API interface information list based on domain ID
     * @param domainId domain id
     * @return API interface information list
     */
    List<ApiDTO> getApiListByDomainId(Long domainId);

    /**
     * Query API pagination list
     * @param apiQueryParam API interface pagination query parameters
     * @return Paging objects for APIs
     */
    PageInfo getApiPageList(ApiQueryParam apiQueryParam);

}
