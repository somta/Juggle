package net.somta.juggle.console.configuration;

import net.somta.core.cache.redis.model.RedisConfigItem;
import net.somta.juggle.core.enums.FlowResultManagerTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author husong
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "juggle")
public class JuggleProperties {

    private String openServerAddr = "https://open.juggle.plus";

    private String apiKey;

    private String secretKey;

    private CacheConfig cache = new CacheConfig();


    public String getOpenServerAddr() {
        return openServerAddr;
    }

    public void setOpenServerAddr(String openServerAddr) {
        this.openServerAddr = openServerAddr;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public CacheConfig getCache() {
        return cache;
    }

    public void setCache(CacheConfig cache) {
        this.cache = cache;
    }

    public static class CacheConfig {
        private FlowResultManagerTypeEnum cacheType = FlowResultManagerTypeEnum.MEMORY;

        private RedisConfigItem redis;

        public FlowResultManagerTypeEnum getCacheType() {
            return cacheType;
        }

        public void setCacheType(FlowResultManagerTypeEnum cacheType) {
            this.cacheType = cacheType;
        }

        public RedisConfigItem getRedis() {
            return redis;
        }

        public void setRedis(RedisConfigItem redis) {
            this.redis = redis;
        }
    }

}
