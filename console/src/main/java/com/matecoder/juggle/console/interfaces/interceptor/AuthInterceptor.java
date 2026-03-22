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
package com.matecoder.juggle.console.interfaces.interceptor;

import com.matecoder.common.utils.JsonUtil;
import com.matecoder.core.context.ApplicationContext;
import com.matecoder.core.protocol.ResponseDataResult;
import com.matecoder.core.context.IdentityContext;
import com.matecoder.core.context.JwtHelper;
import com.matecoder.juggle.common.constants.ApplicationConstants;
import com.matecoder.juggle.console.application.service.system.ITokenService;
import com.matecoder.juggle.console.configuration.JuggleProperties;
import com.matecoder.juggle.console.domain.system.token.TokenEntity;
import com.matecoder.juggle.console.domain.system.token.vo.OpenApiTokenVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import static com.matecoder.juggle.common.constants.ApplicationConstants.JUGGLE_OPEN_API_PREFIX;
import static com.matecoder.juggle.console.domain.user.enums.UserErrorEnum.OPEN_API_TOKEN_ERROR;
import static com.matecoder.juggle.console.domain.user.enums.UserErrorEnum.USER_NOT_LOGIN_ERROR;

/**
 * @author husong
 * @since 1.0.0
 */
public class AuthInterceptor implements AsyncHandlerInterceptor {

    private final JuggleProperties juggleProperties;
    private final ITokenService tokenService;

    public AuthInterceptor(JuggleProperties juggleProperties, ITokenService tokenService) {
        this.juggleProperties = juggleProperties;
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
        ApplicationContext.removeIdentityContext();
    }

    private boolean handleApi(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        String token = request.getHeader(ApplicationConstants.TOKEN_HEADER_KEY);
        Boolean isExpired = JwtHelper.verifyExpired(token,juggleProperties.getSecretKey());
        if(isExpired){
            //错误信息响应到前台
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JsonUtil.serialize(ResponseDataResult.setErrorResponseResult(USER_NOT_LOGIN_ERROR)));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        IdentityContext identityContext = JwtHelper.parseToken(token,juggleProperties.getSecretKey());
//        boolean isAllowRequest = hasRequestUrlPerm(identityContext.getUserId(),request.getRequestURI());
//        if(!isAllowRequest){
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().print(JsonUtil.serialize(ResponseDataResult.setErrorResponseResult(USER_FORBIDDEN_ERROR)));
//            response.setStatus(HttpStatus.FORBIDDEN.value());
//            return false;
//        }

        ApplicationContext.putIdentityContext(identityContext.getUserId(),identityContext.getTenantId(),null);


        return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean handleOpenApi(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        String token = request.getHeader(ApplicationConstants.OPEN_API_HEADER_KEY);
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(ApplicationConstants.OPEN_API_PARAM_KEY);
        }
        Boolean tokenExistFlag = tokenService.isExistToken(token);
        if(StringUtils.isEmpty(token) || !tokenExistFlag){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JsonUtil.serialize(ResponseDataResult.setErrorResponseResult(OPEN_API_TOKEN_ERROR)));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        TokenEntity tokenEntity = new TokenEntity();
        OpenApiTokenVO openApiTokenVo = tokenEntity.parseTokenValue(token);
        ApplicationContext.putIdentityContext(openApiTokenVo.getUserId(),null,null);
        return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }
}