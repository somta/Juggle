package net.somta.juggle.console.configuration;

import net.somta.common.utils.SnowflakeIdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public SnowflakeIdUtil snowflakeIdUtil(){
        return new SnowflakeIdUtil();
    }
}
