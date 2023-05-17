package net.somta.juggle.core.result;

import org.apache.commons.collections4.map.LRUMap;

import java.util.Map;

public class MemoryFlowResultManager implements IFlowResultManager {

    private LRUMap<String, Map<String,Object>> result = new LRUMap<>(2000,500);

    @Override
    public boolean putFlowResult(String flowInstanceId,Map<String,Object> resultData) {
        result.put(flowInstanceId,resultData);
        return true;
    }

    @Override
    public Map<String, Object> getFlowResult(String flowInstanceId) {
        return result.get(flowInstanceId);
    }
}
