package net.somta.juggle.console.application.service.flow.impl;

import net.somta.core.cache.redis.RedisClientBuilder;
import net.somta.core.cache.redis.client.AbstractRedisClient;
import net.somta.core.cache.redis.model.RedisConfigItem;
import net.somta.juggle.console.application.service.flow.IFlowRuntimeService;
import net.somta.juggle.console.configuration.JuggleProperties;
import net.somta.juggle.console.configuration.JuggleProperties.*;
import net.somta.juggle.console.domain.flow.flowinfo.enums.FlowTypeEnum;
import net.somta.juggle.common.param.TriggerDataParam;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.AsyncDispatcher;
import net.somta.juggle.core.dispatcher.impl.SyncDispatcher;
import net.somta.juggle.core.enums.FlowResultManagerTypeEnum;
import net.somta.juggle.core.model.*;
import net.somta.juggle.core.result.IFlowResultManager;
import net.somta.juggle.core.result.MemoryFlowResultManager;
import net.somta.juggle.core.result.RedisFlowResultManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Gavin
 */
@Service
public class FlowRuntimeServiceImpl implements IFlowRuntimeService {

    private IFlowResultManager flowResultManager;
    private final JuggleProperties juggleProperties;

    private final IDispatcher dispatcher = new AsyncDispatcher();

    public FlowRuntimeServiceImpl(JuggleProperties juggleProperties) {
        this.juggleProperties = juggleProperties;
        initFlowResultManager(juggleProperties);
    }

    @Override
    public FlowResult triggerFlow(Flow flow, String flowType, TriggerDataParam triggerData) {
        String flowInstanceId = flowType + "_" + RandomStringUtils.random(10, true, true);
        flow.setFlowInstanceId(flowInstanceId);

        FlowResult flowResult = null;
        if(FlowTypeEnum.ASYNC.getCode().equals(flowType)){
            flowResult = dispatcher.doDispatcher(flow,triggerData.getFlowData(),flowResultManager);
        }else{
            IDispatcher dispatcher = new SyncDispatcher();
            flowResult = dispatcher.doDispatcher(flow,triggerData.getFlowData(),flowResultManager);
        }
        flowResult.setFlowInstanceId(flowInstanceId);
        return flowResult;
    }


    @Override
    public Map<String, Object> getAsyncFlowResult(String flowInstanceId) {
        return flowResultManager.getFlowResult(flowInstanceId);
    }

    private void initFlowResultManager(JuggleProperties juggleProperties) {
        if(juggleProperties.getCacheType().equalsIgnoreCase(FlowResultManagerTypeEnum.MEMORY.name())){
            flowResultManager = new MemoryFlowResultManager();
        }else if(juggleProperties.getCacheType().equalsIgnoreCase(FlowResultManagerTypeEnum.REDIS.name())) {
            RedisConfig redisConfig = juggleProperties.getRedis();
            RedisConfigItem redisConfigItem = new RedisConfigItem();
            redisConfigItem.setModel(redisConfig.getModel());
            redisConfigItem.setAddress(redisConfig.getAddress());
            if(StringUtils.isNotBlank(redisConfig.getPassword())){
                redisConfigItem.setPassword(redisConfig.getPassword());
            }
            if(StringUtils.isNotBlank(redisConfig.getSentinelMaster())){
                redisConfigItem.setSentinelMaster(redisConfig.getSentinelMaster());
            }
            AbstractRedisClient redisClient = RedisClientBuilder.buildRedisClient(redisConfigItem);
            flowResultManager = new RedisFlowResultManager(redisClient);
        }else {

        }
    }
}
