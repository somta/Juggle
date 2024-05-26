package net.somta.juggle.client;

/**
 * @author husong
 * @since 1.2.0
 */
public class JuggleConfig {

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
