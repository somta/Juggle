package net.somta.juggle.console.configuration;

import net.somta.common.utils.SnowflakeIdUtil;
import net.somta.juggle.core.result.IFlowResultManager;
import net.somta.juggle.core.result.MemoryFlowResultManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
