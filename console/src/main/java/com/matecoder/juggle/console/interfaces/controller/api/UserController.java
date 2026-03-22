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
package com.matecoder.juggle.console.interfaces.controller.api;

import com.matecoder.core.context.IdentityContext;
import com.matecoder.core.context.JwtHelper;
import com.matecoder.juggle.common.constants.ApplicationConstants;
import com.matecoder.juggle.console.configuration.JuggleProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.matecoder.core.protocol.ResponseDataResult;
import com.matecoder.juggle.console.domain.user.UserAO;
import com.matecoder.juggle.console.domain.user.enums.UserErrorEnum;
import com.matecoder.juggle.console.interfaces.dto.LoginDTO;
import com.matecoder.juggle.console.interfaces.dto.UserDTO;
import com.matecoder.juggle.console.interfaces.listener.JuggleApplicationRunListener;
import com.matecoder.juggle.console.interfaces.param.user.LoginParam;
import com.matecoder.juggle.console.interfaces.param.user.UpdatePasswordParam;
import com.matecoder.juggle.console.application.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.matecoder.juggle.common.constants.ApplicationConstants.JUGGLE_API_PREFIX;


/**
 * @author Gavin
 * @since 1.0.0
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(JUGGLE_API_PREFIX + "/user")
public class UserController {

    private final JuggleProperties juggleProperties;
    private final IUserService userService;

    public UserController(JuggleProperties juggleProperties, IUserService userService) {
        this.juggleProperties = juggleProperties;
        this.userService = userService;
    }

    @Operation(summary = "登录")
    @PostMapping("/login")
    public ResponseDataResult<LoginDTO> login(@RequestBody LoginParam loginParam){
        LoginDTO loginDTO = new LoginDTO();
        if(StringUtils.isEmpty(loginParam.getUserName()) || StringUtils.isEmpty(loginParam.getPassword())){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.LOGIN_PARAM_ERROR);
        }
        UserAO userAo = userService.queryUserByUserName(loginParam.getUserName());
        if(userAo == null){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.USER_NOT_EXIST_ERROR);
        }
        if(loginParam.getPassword().equals(userAo.getPassword())){
            Map<String, Object> payload = new HashMap<>(4);
            payload.put(IdentityContext.USER_ID, userAo.getId().toString());
            String token = JwtHelper.generateToken(ApplicationConstants.ISSUER,payload,juggleProperties.getSecretKey());;
            loginDTO.setUserName(userAo.getUserName());
            loginDTO.setToken(token);
            return ResponseDataResult.setResponseResult(loginDTO);
        }else {
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.USER_PWD_ERROR);
        }
    }

    @Operation(summary = "获取用户信息")
    @PostMapping("/getUserInfo")
    public ResponseDataResult<UserDTO> getUserInfo(HttpServletRequest request){
        UserDTO userDTO = new UserDTO();
        String token = request.getHeader(ApplicationConstants.TOKEN_HEADER_KEY);
        IdentityContext identityContext = JwtHelper.parseToken(token,juggleProperties.getSecretKey());
        UserAO userAo = userService.queryUserById(identityContext.getUserId());
        userDTO.setId(userAo.getId());
        userDTO.setUserName(userAo.getUserName());
        return ResponseDataResult.setResponseResult(userDTO);
    }

    @Operation(summary = "修改密码")
    @PutMapping("/updatePassword")
    public ResponseDataResult<Boolean> updatePassword(UpdatePasswordParam updatePasswordParam){
        UserAO userAo = userService.queryUserById(updatePasswordParam.getUserId());
        if(userAo == null){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.USER_NOT_EXIST_ERROR);
        }
        if(!updatePasswordParam.getOldPassword().equals(userAo.getPassword())){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.OLD_PASSWORD_ERROR);
        }
        userAo.setPassword(updatePasswordParam.getNewPassword());
        userService.updateUser(userAo);
        return ResponseDataResult.setResponseResult(true);
    }

    @Operation(summary = "获取产品信息")
    @GetMapping("/product/info")
    public ResponseDataResult<String> getProductInfo(){
        Package pkg = JuggleApplicationRunListener.class.getPackage();
        String version = pkg.getImplementationVersion();
        return ResponseDataResult.setResponseResult(version);
    }
}
