package net.somta.juggle.core.variable;

import net.somta.juggle.core.model.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于内存的变量管 理器
 * @author husong
 * @date 2022/2/13
 **/
public class MemoryVariableManager extends VariableManager{

    private final Map<String,Object> variableValueMap;

    public MemoryVariableManager(Map<String, Variable> variableSchemaMap) {
        super(variableSchemaMap);
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
