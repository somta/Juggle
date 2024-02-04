package net.somta.juggle.starter;

import net.somta.juggle.starter.impl.IJuggleTemplate;
import net.somta.juggle.starter.impl.JuggleTemplateImpl;
import net.somta.juggle.starter.properties.JuggleOpenProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author husong
 */
@Configuration
@EnableConfigurationProperties({JuggleOpenProperties.class})
public class JuggleAutoConfiguration {

    @Bean
    public IJuggleTemplate juggleTemplate(JuggleOpenProperties juggleOpenProperties){
        if (juggleOpenProperties == null) {
            throw new IllegalArgumentException("juggleOpenProperties is null");
        }
        if(StringUtils.isEmpty(juggleOpenProperties.getServerAddr())){
            throw new IllegalArgumentException("serverAddr is null");
        }
        if(StringUtils.isEmpty(juggleOpenProperties.getCredential())){
            throw new IllegalArgumentException("credential is null");
        }
        return new JuggleTemplateImpl(juggleOpenProperties);
    }
}
