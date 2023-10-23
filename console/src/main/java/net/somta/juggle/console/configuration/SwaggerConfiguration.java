package net.somta.juggle.console.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置类
 * @author husong
 **/
@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi userApi(){
        String[] paths = { "/**" };
        String[] packagedToMatch = { "net.somta.juggle.console.interfaces.controller" };
        return GroupedOpenApi.builder().group("API接口")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Juggle微服务编排系统API")
                        .version("1.0")
                        .description( "Juggle是一个微服务编排框架")
                        .termsOfService("http://somta.net")
                        .license(new License().name("Apache 2.0")
                        .url("http://somta.net")));
    }



}