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
package net.somta.juggle.console.configuration;

import net.somta.juggle.console.application.service.system.ITokenService;
import net.somta.juggle.console.interfaces.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_API_PREFIX;

/**
 * @author husong
 * @since 1.0.0
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final ITokenService tokenService;

    public InterceptorConfiguration(ITokenService tokenService) {
        this.tokenService = tokenService;
    }


    /**
     * 添加自定义的拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(tokenService))
                .addPathPatterns("/api/**")
                .excludePathPatterns(JUGGLE_API_PREFIX + "/user/login","/pub/**");
    }
}