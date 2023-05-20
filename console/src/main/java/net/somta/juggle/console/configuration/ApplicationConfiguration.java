package net.somta.juggle.console.configuration;

import net.somta.common.utils.SnowflakeIdUtil;
import net.somta.juggle.console.properties.JuggleProperties;
import net.somta.juggle.core.result.IFlowResultManager;
import net.somta.juggle.core.result.MemoryFlowResultManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(JuggleProperties.class)
@Configuration
public class ApplicationConfiguration {

    @Bean
    public SnowflakeIdUtil snowflakeIdUtil(){
        return new SnowflakeIdUtil();
    }

    @Bean
    public IFlowResultManager flowResultManager(){
        return new MemoryFlowResultManager();
    }


}
