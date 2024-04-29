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
package net.somta.juggle.console.application.service.flow.impl;

import net.somta.core.cache.redis.RedisClientBuilder;
import net.somta.core.cache.redis.client.AbstractRedisClient;
import net.somta.core.cache.redis.model.RedisConfigItem;
import net.somta.juggle.console.application.service.flow.IFlowRuntimeService;
import net.somta.juggle.console.application.service.system.IDataSourceService;
import net.somta.juggle.console.configuration.JuggleProperties;
import net.somta.juggle.console.domain.flow.flowinfo.enums.FlowTypeEnum;
import net.somta.juggle.common.param.TriggerDataParam;
import net.somta.juggle.console.application.service.system.impl.DataSourceManager;
import net.somta.juggle.console.domain.system.datasource.repository.IDataSourceRepository;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.AsyncDispatcher;
import net.somta.juggle.core.dispatcher.impl.SyncDispatcher;
import net.somta.juggle.core.enums.FlowResultManagerTypeEnum;
import net.somta.juggle.core.executor.data.IDataSourceManager;
import net.somta.juggle.core.model.*;
import net.somta.juggle.core.result.IFlowResultManager;
import net.somta.juggle.core.result.MemoryFlowResultManager;
import net.somta.juggle.core.result.RedisFlowResultManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Gavin
 * @since 1.0.0
 */
@Service
public class FlowRuntimeServiceImpl implements IFlowRuntimeService {

    private IFlowResultManager flowResultManager;
    private final IDataSourceManager dataSourceManager;

    private final IDispatcher dispatcher = new AsyncDispatcher();

    public FlowRuntimeServiceImpl(JuggleProperties juggleProperties, IDataSourceRepository dataSourceRepository) {
        initFlowResultManager(juggleProperties);
        this.dataSourceManager = new DataSourceManager(dataSourceRepository);
    }

    @Override
    public FlowResult triggerFlow(Flow flow, String flowType, TriggerDataParam triggerData) {
        String flowInstanceId = flowType + "_" + RandomStringUtils.random(16, true, true);
        flow.setFlowInstanceId(flowInstanceId);

        FlowResult flowResult;
        if(FlowTypeEnum.ASYNC.getCode().equals(flowType)){
            flowResult = dispatcher.doDispatcher(flow,triggerData.getFlowData(),flowResultManager,dataSourceManager);
        }else{
            IDispatcher dispatcher = new SyncDispatcher();
            flowResult = dispatcher.doDispatcher(flow,triggerData.getFlowData(),flowResultManager,dataSourceManager);
        }
        flowResult.setFlowInstanceId(flowInstanceId);
        return flowResult;
    }


    @Override
    public Map<String, Object> getAsyncFlowResult(String flowInstanceId) {
        return flowResultManager.getFlowResult(flowInstanceId);
    }

    private void initFlowResultManager(JuggleProperties juggleProperties) {
        if(juggleProperties.getCache().getCacheType().equals(FlowResultManagerTypeEnum.MEMORY)){
            flowResultManager = new MemoryFlowResultManager();
        }else if(juggleProperties.getCache().getCacheType().equals(FlowResultManagerTypeEnum.REDIS)) {
            RedisConfigItem redisConfigItem = juggleProperties.getCache().getRedis();
            AbstractRedisClient redisClient = RedisClientBuilder.buildRedisClient(redisConfigItem);
            flowResultManager = new RedisFlowResultManager(redisClient);
        }else {
            throw new IllegalArgumentException("非法的缓存类型");
        }
    }
}
