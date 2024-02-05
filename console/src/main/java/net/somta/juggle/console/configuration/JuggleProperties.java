package net.somta.juggle.console.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author husong
 * @date 2023/05/20
 */
@ConfigurationProperties(prefix = "juggle")
public class JuggleProperties {

    // todo 这里配置不合理
    private String cacheType = "memory";
    private RedisConfig redis;

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public RedisConfig getRedis() {
        return redis;
    }

    public void setRedis(RedisConfig redis) {
        this.redis = redis;
    }

    public static class RedisConfig {
        //todo 这里要优化的以前用枚举
        private String model = "single";
        /**
         * Redis地址
         */
        private String[] address;
        /**
         * Redis密码
         */
        private String password;
        private String sentinelMaster;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String[] getAddress() {
            return address;
        }

        public void setAddress(String[] address) {
            this.address = address;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSentinelMaster() {
            return sentinelMaster;
        }

        public void setSentinelMaster(String sentinelMaster) {
            this.sentinelMaster = sentinelMaster;
        }
    }

}
