package net.somta.juggle.console.configuration;

import net.somta.juggle.core.result.IFlowResultManager;
import net.somta.juggle.core.result.MemoryFlowResultManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author husong
 */
@EnableConfigurationProperties(JuggleProperties.class)
@Configuration
public class ApplicationConfiguration {


}
