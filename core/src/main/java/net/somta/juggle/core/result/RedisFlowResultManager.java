package net.somta.juggle.core.result;

import net.somta.core.cache.redis.client.AbstractRedisClient;
import net.somta.core.helper.JsonSerializeHelper;
import org.apache.commons.collections4.map.LRUMap;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author husong
 */
public class RedisFlowResultManager implements IFlowResultManager {

    private final RedissonClient redissonClient;

    public RedisFlowResultManager(AbstractRedisClient redisClient) {
        redissonClient = redisClient.getRedissonClient();
    }

    @Override
    public boolean putFlowResult(String flowInstanceId,Map<String,Object> resultData) {
        RMap map = redissonClient.getMap(flowInstanceId);
        map.putAll(resultData);
        return true;
    }

    @Override
    public Map<String, Object> getFlowResult(String flowInstanceId) {
        RMap map = redissonClient.getMap(flowInstanceId);
        Map<String, Object> cacheMap = map.readAllMap();
        Map<String, Object> rstMap = new HashMap<>(16);
        for (String mapKey : cacheMap.keySet()) {
            Object value = cacheMap.get(mapKey);
            rstMap.put(mapKey,value);
        }
        return rstMap;
    }
}
