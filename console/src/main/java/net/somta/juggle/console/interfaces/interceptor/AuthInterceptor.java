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
package net.somta.juggle.console.interfaces.interceptor;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.common.identity.IdentityVO;
import net.somta.juggle.common.utils.JwtUtil;
import net.somta.juggle.console.application.service.system.ITokenService;
import net.somta.juggle.console.domain.system.token.TokenEntity;
import net.somta.juggle.console.domain.system.token.vo.OpenApiTokenVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_OPEN_API_PREFIX;
import static net.somta.juggle.console.domain.user.enums.UserErrorEnum.OPEN_API_TOKEN_ERROR;
import static net.somta.juggle.console.domain.user.enums.UserErrorEnum.USER_NOT_LOGIN_ERROR;

/**
 * @author husong
 * @since 1.0.0
 */
public class AuthInterceptor implements AsyncHandlerInterceptor {

    private final ITokenService tokenService;

    public AuthInterceptor(ITokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if(path.startsWith(JUGGLE_OPEN_API_PREFIX)){
            return handleOpenApi(request,response,handler);
        }else {
            return handleApi(request,response,handler);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        IdentityContext.clearIdentity();
    }

    private boolean handleApi(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        String token = request.getHeader(JwtUtil.TOKEN_HEADER_KEY);
        Boolean isExpired = JwtUtil.verifyExpired(token);
        if(isExpired){
            //错误信息响应到前台
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JsonSerializeHelper.serialize(ResponseDataResult.setErrorResponseResult(USER_NOT_LOGIN_ERROR)));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        IdentityVO identityVo = JwtUtil.parseToken(token);
        IdentityContext.setIdentity(new IdentityVO(identityVo.getUserId()));
        return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean handleOpenApi(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        String token = request.getHeader(JwtUtil.OPEN_API_HEADER_KEY);
        if(StringUtils.isEmpty(token)){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JsonSerializeHelper.serialize(ResponseDataResult.setErrorResponseResult(OPEN_API_TOKEN_ERROR)));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        Boolean flag = tokenService.isExistToken(token);
        //todo 解析token，调用数据库是否存在
        TokenEntity tokenEntity = new TokenEntity();
        OpenApiTokenVO openApiTokenVo = tokenEntity.parseTokenValue(token);
        IdentityContext.setIdentity(new IdentityVO(openApiTokenVo.getUserId()));
        return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }
}