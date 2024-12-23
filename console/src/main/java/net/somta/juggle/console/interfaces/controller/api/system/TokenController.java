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
package net.somta.juggle.console.interfaces.controller.api.system;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.base.page.PageParam;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.system.ITokenService;
import net.somta.juggle.console.interfaces.dto.system.TokenDTO;
import net.somta.juggle.console.interfaces.param.system.TokenAddParam;
import net.somta.juggle.console.interfaces.param.system.TokenUpdateParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 * @since 1.1.0
 */
@Tag(name = "令牌接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/token")
public class TokenController {

    private final ITokenService tokenService;

    public TokenController(ITokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Operation(summary = "新增令牌")
    @PostMapping("/add")
    public ResponseDataResult<String> addToken(@RequestBody TokenAddParam tokenAddParam){
        String tokenValue = tokenService.addToken(tokenAddParam.getTokenDesc());
        return ResponseDataResult.setResponseResult(tokenValue);
    }

    @Operation(summary = "根据ID删除令牌")
    @DeleteMapping("/delete/{tokenId}")
    public ResponseDataResult<Boolean> deleteToken(@PathVariable Long tokenId){
        tokenService.deleteToken(tokenId);
        return ResponseDataResult.setResponseResult(true);
    }

    @Operation(summary = "修改令牌")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateToken(@RequestBody TokenUpdateParam tokenUpdateParam){
        tokenService.updateToken(tokenUpdateParam);
        return ResponseDataResult.setResponseResult(true);
    }


    @Operation(summary = "查询令牌分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<TokenDTO> getTokenPageList(@RequestBody PageParam pageParam){
        PageInfo pageInfo = tokenService.getTokenPageList(pageParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }

}
