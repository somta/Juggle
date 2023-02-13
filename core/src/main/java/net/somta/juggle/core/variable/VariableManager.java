package net.somta.juggle.core.variable;

import net.somta.core.exception.BizException;
import net.somta.juggle.core.enums.ErrorEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.DataTypeInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 变量管理器
 * @author husong
 * @date 2022/2/13
 **/
public abstract class VariableManager {
    private Map<String, DataTypeInfo> variableSchemaMap;

    Object getVariableValue(String key) throws FlowException{
        if(StringUtils.isEmpty(key)){
            throw new FlowException(ErrorEnum.ENV_KEY_ERROR);
        }
        return doGetVariableValue(key);
    }

    Boolean setVariableValue(String key,String value) throws FlowException {
        if(StringUtils.isEmpty(key)){
            throw new FlowException(ErrorEnum.ENV_KEY_ERROR);
        }
        return doSetVariableValue(key,value);
    }

    protected abstract Object doGetVariableValue(String key);

    protected abstract Boolean doSetVariableValue(String key,Object value);

}
