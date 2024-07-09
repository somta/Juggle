package net.somta.juggle.console.application.service.suite;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.dto.suite.ApiDTO;
import net.somta.juggle.console.interfaces.dto.suite.ApiInfoDTO;
import net.somta.juggle.console.interfaces.param.suite.ApiAddParam;
import net.somta.juggle.console.interfaces.param.suite.ApiDebugParam;
import net.somta.juggle.console.interfaces.param.suite.ApiQueryParam;
import net.somta.juggle.console.interfaces.param.suite.ApiUpdateParam;

import java.util.List;
import java.util.Map;

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
     * @param suiteId suite id
     * @return API interface information list
     */
    List<ApiDTO> getApiListBySuiteId(Long suiteId);

    /**
     * Query API pagination list
     * @param apiQueryParam API interface pagination query parameters
     * @return Paging objects for APIs
     */
    PageInfo getApiPageList(ApiQueryParam apiQueryParam);

    /**
     * Debugging API interfaces
     * @param apiId api id
     * @param apiDebugParam API interface input parameter data
     * @return Response results of API interface
     */
    Map<String, Object> debugApi(Long apiId, ApiDebugParam apiDebugParam);
}
