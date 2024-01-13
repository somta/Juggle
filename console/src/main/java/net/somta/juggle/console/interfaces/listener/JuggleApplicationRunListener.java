package net.somta.juggle.console.interfaces.listener;

import net.somta.juggle.common.utils.InetUtil;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

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
    }

    private void initBannerEnv(){
        System.setProperty("server.ip", InetUtil.getLocalIP());
    }

}