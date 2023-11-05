package net.somta.juggle.console.configuration;

import net.somta.juggle.console.interfaces.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author husong
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
 
    /**
     * 添加自定义的拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // todo 先把登录校验关掉
        /*registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/v1/**")
                .excludePathPatterns("/v1/user/login","/v1/flow/version/trigger/**","/v1/flow/version/getAsyncFlowResult/*");*/
    }
}