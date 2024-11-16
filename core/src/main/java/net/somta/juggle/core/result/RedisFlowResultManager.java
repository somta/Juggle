/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/

package net.somta.juggle.core.result;

import net.somta.core.cache.redis.client.AbstractRedisClient;
import net.somta.core.helper.JsonSerializeHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.LRUMap;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author husong
 * @since 1.0.0
 */
public class RedisFlowResultManager implements IFlowResultManager {

    private final RedissonClient redissonClient;

    public RedisFlowResultManager(AbstractRedisClient redisClient) {
        redissonClient = redisClient.getRedissonClient();
    }

    @Override
    public boolean putFlowResult(String flowInstanceId,Map<String,Object> resultData) {
        RMap map = redissonClient.getMap(flowInstanceId);
        Map<String, Object> noNullValueResultMap = resultData;
        if(resultData != null) {
            noNullValueResultMap = resultData.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() != null)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        map.putAll(noNullValueResultMap);
        return true;
    }

    @Override
    public Map<String, Object> getFlowResult(String flowInstanceId) {
        RMap map = redissonClient.getMap(flowInstanceId);
        Map<String, Object> cacheMap = map.readAllMap();
        Map<String, Object> resultMap = new HashMap<>(16);
        for (String mapKey : cacheMap.keySet()) {
            Object value = cacheMap.get(mapKey);
            resultMap.put(mapKey,value);
        }
        return resultMap;
    }
}
