package net.somta.juggle.console.interfaces.listener;

import net.somta.juggle.common.utils.InetUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.env.ConfigurableEnvironment;


/**
 * @author husong
 */
public class JuggleApplicationRunListener implements SpringApplicationRunListener {
    private SpringApplication application;
    private String[] args;

    public JuggleApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        initBannerEnv();
        String apiKey = environment.getProperty("juggle.api-key");
        if(StringUtils.isNotEmpty(apiKey)){
            System.setProperty("apiKey",apiKey);
        }
    }

    private void initBannerEnv(){
        System.setProperty("server.ip", InetUtil.getLocalIp());
        Package pkg = JuggleApplicationRunListener.class.getPackage();
        String version = pkg.getImplementationVersion();
        if(StringUtils.isNotEmpty(version)){
            System.setProperty("application.version", version);
        }
    }

}