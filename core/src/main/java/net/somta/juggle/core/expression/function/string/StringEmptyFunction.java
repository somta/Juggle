package net.somta.juggle.core.expression.function.string;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * @author husong
 */
public class StringEmptyFunction extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject targetArg) {
        String target = FunctionUtils.getStringValue(targetArg, env);
        if(target == null){
            return AviatorBoolean.TRUE;
        } else {
            return target.length() <= 0 ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;
        }
    }

    @Override
    public String getName() {
        return "string.empty";
    }
}
