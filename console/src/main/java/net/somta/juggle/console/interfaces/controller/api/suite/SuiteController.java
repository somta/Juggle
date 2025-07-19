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
package net.somta.juggle.console.interfaces.controller.api.suite;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.suite.IApiService;
import net.somta.juggle.console.application.service.suite.ISuiteService;
import net.somta.juggle.console.domain.suite.suiteinfo.enums.SuiteErrorEnum;
import net.somta.juggle.console.interfaces.dto.suite.*;
import net.somta.juggle.console.interfaces.param.suite.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_API_PREFIX;
import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 * @since 1.1.1
 */
@Tag(name = "套件接口")
@RestController
@RequestMapping(JUGGLE_API_PREFIX + "/suite")
public class SuiteController {
    private final ISuiteService suiteService;
    private final IApiService apiService;

    public SuiteController(ISuiteService suiteService, IApiService apiService) {
        this.suiteService = suiteService;
        this.apiService = apiService;
    }

    @Operation(summary = "新增套件")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addSuite(@RequestBody SuiteAddParam suiteAddParam){
        suiteService.addSuite(suiteAddParam);
        return ResponseDataResult.setResponseResult(true);
    }

    @Operation(summary = "修改套件")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateSuite(@RequestBody SuiteUpdateParam suiteUpdateParam){
        suiteService.updateSuite(suiteUpdateParam);
        return ResponseDataResult.setResponseResult(true);
    }

    @Operation(summary = "根据ID删除套件")
    @DeleteMapping("/delete/{suiteId}")
    public ResponseDataResult<Boolean> deleteSuite(@PathVariable Long suiteId){
        List<ApiDTO> apis = apiService.getApiListBySuiteId(suiteId);
        if(CollectionUtils.isNotEmpty(apis)){
            return ResponseDataResult.setErrorResponseResult(SuiteErrorEnum.SUITE_EXIST_API_ERROR);
        }
        suiteService.deleteSuite(suiteId);
        return ResponseDataResult.setResponseResult(true);
    }

    @Operation(summary = "查询套件列表")
    @GetMapping("/list")
    public ResponseDataResult<List<SuiteDTO>> getAllSuiteList(){
        List<SuiteDTO> list = suiteService.getAllSuiteList();
        return ResponseDataResult.setResponseResult(list);
    }

    @Operation(summary = "查询套件分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<SuiteDTO> getSuitePageList(@RequestBody SuiteQueryParam suiteQueryParam){
        PageInfo pageInfo = suiteService.getSuitePageList(suiteQueryParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Operation(summary = "查询市场套件分类列表")
    @PostMapping("/market/classify")
    public ResponseDataResult<List<SuiteMarketClassifyDTO>> getSuiteMarketClassifyList(){
        List<SuiteMarketClassifyDTO> list = suiteService.getSuiteMarketClassifyList();
        return ResponseDataResult.setResponseResult(list);
    }

    @Operation(summary = "查询市场套件列表")
    @PostMapping("/market")
    public ResponsePaginationDataResult<SuiteDTO> getSuiteMarketList(@RequestBody SuiteMarketQueryParam suiteMarketQueryParam){
        return suiteService.getSuiteMarketList(suiteMarketQueryParam);
    }

    @Operation(summary = "查询市场套件详情")
    @GetMapping("/market/info/{suiteId}")
    public ResponseDataResult<SuiteMarketInfoDTO> getSuiteMarketInfo(@PathVariable Long suiteId){
        SuiteMarketInfoDTO suiteMarketDto = suiteService.getSuiteMarketInfo(suiteId);
        return ResponseDataResult.setResponseResult(suiteMarketDto);
    }

    @Operation(summary = "安装市场套件")
    @PostMapping("/market/install")
    public ResponseDataResult<Boolean> installSuiteMarket(@RequestBody SuiteMarketParam suiteMarketParam){
        suiteService.installSuiteMarket(suiteMarketParam);
        return ResponseDataResult.setResponseResult();
    }


}
