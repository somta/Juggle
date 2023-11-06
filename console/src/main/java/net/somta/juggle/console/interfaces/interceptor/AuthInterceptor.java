package net.somta.juggle.console.interfaces.interceptor;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static net.somta.juggle.console.domain.user.enums.UserErrorEnum.USER_NOT_LOGIN_ERROR;

/**
 * @author husong
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JwtUtil.TOKEN_HEADER_KEY);
        Boolean isExpired = JwtUtil.verifyExpired(token);
        if(!isExpired){
            return true;
        }
        //错误信息响应到前台
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JsonSerializeHelper.serialize(ResponseDataResult.setErrorResponseResult(USER_NOT_LOGIN_ERROR)));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }
}