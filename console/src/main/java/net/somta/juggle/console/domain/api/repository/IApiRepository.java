package net.somta.juggle.console.domain.api.repository;

import net.somta.juggle.console.domain.api.ApiAO;

/**
 * @author Gavin
 */
public interface IApiRepository {

    Boolean addApi(ApiAO apiAO);

    Boolean deleteApi(Long apiId);

    Boolean updateApi(ApiAO apiAO);

    ApiAO queryApi(Long apiId);

    void getApiListByDomainId(Long domainId);
}
