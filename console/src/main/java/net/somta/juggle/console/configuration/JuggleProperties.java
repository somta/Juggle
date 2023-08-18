package net.somta.juggle.console.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author husong
 * @date 2023/05/20
 */
@ConfigurationProperties(prefix = "juggle")
public class JuggleProperties {

    private String dbType = "h2";

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
}
