package net.somta.juggle.core.expression.condition.function.object;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author husong
 */
public class ObjectEmptyFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject targetArg) {
        Object obj = FunctionUtils.getJavaObject(targetArg,env);
        if(obj == null){
            return AviatorBoolean.TRUE;
        } else {
            return AviatorBoolean.FALSE;
        }
    }

    @Override
    public String getName() {
        return "object.empty";
    }
}
