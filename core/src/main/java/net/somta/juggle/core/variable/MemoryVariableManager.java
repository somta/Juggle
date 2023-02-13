package net.somta.juggle.core.variable;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于内存的变量管理器
 * @author husong
 * @date 2022/2/13
 **/
public class MemoryVariableManager extends VariableManager{

    private final Map<String,Object> variableValueMap;

    public MemoryVariableManager() {
        this.variableValueMap = new HashMap<>();
    }

    @Override
    protected Object doGetVariableValue(String key) {
        Object value = variableValueMap.get(key);
        return value;
    }

    @Override
    protected Boolean doSetVariableValue(String key, Object value) {
        variableValueMap.put(key,value);
        return true;
    }
}
