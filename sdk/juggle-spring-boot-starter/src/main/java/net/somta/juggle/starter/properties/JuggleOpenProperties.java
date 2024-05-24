package net.somta.juggle.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author husong
 */
@ConfigurationProperties(prefix = "juggle")
public class JuggleOpenProperties {
    /**
     * Juggle server address
     */
    private String serverAddr;

    /**
     * Request access token
     */
    private String accessToken;

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
