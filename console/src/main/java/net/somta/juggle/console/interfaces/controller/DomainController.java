/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.interfaces.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.domain.domain.enums.DomainErrorEnum;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.interfaces.dto.DomainDTO;
import net.somta.juggle.console.interfaces.param.DomainAddParam;
import net.somta.juggle.console.interfaces.param.DomainQueryParam;
import net.somta.juggle.console.interfaces.param.DomainUpdateParam;
import net.somta.juggle.console.application.service.IApiService;
import net.somta.juggle.console.application.service.IDomainService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 * @since 1.0.0
 */
@Tag(name = "领域接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/domain")
public class DomainController {

    private final IDomainService domainService;
    private final IApiService apiService;

    public DomainController(IDomainService domainService, IApiService apiService) {
        this.domainService = domainService;
        this.apiService = apiService;
    }

    /**
     * 新增领域
     * @return Boolean
     */
    @Operation(summary = "新增领域")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addDomain(@RequestBody DomainAddParam domainAddParam){
        domainService.addDomain(domainAddParam);
        return ResponseDataResult.setResponseResult(true);
    }

    /**
     * 修改领域
     * @return Boolean
     */
    @Operation(summary = "修改领域")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateDomain(@RequestBody DomainUpdateParam domainUpdateParam){
        domainService.updateDomain(domainUpdateParam);
        return ResponseDataResult.setResponseResult(true);
    }

    @Operation(summary = "根据ID删除领域")
    @DeleteMapping("/delete/{domainId}")
    public ResponseDataResult<Boolean> deleteDomain(@PathVariable Long domainId){
        List<ApiDTO> apis = apiService.getApiListByDomainId(domainId);
        if(CollectionUtils.isNotEmpty(apis)){
            return ResponseDataResult.setErrorResponseResult(DomainErrorEnum.DOMAIN_EXIST_API_ERROR);
        }
        domainService.deleteDomain(domainId);
        return ResponseDataResult.setResponseResult(true);
    }

    /**
     * 查询领域列表
     * @return Boolean
     */
    @Operation(summary = "查询领域列表")
    @GetMapping("/list")
    public ResponseDataResult<List<DomainDTO>> getAllDomainList(){
        List<DomainDTO> list = domainService.getAllDomainList();
        return ResponseDataResult.setResponseResult(list);
    }

    /**
     * 查询领域列表
     * @return Boolean
     */
    @Operation(summary = "查询领域分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<List<DomainDTO>> getDomainPageList(@RequestBody DomainQueryParam domainQueryParam){
        PageInfo pageInfo = domainService.getDomainPageList(domainQueryParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }

}
