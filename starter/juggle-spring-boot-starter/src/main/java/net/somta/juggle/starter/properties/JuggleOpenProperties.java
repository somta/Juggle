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
     * Request credentials
     */
    private String credential;

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
