package net.somta.juggle.core.expression.condition.function.date;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author husong
 */
public class DateEqFunction extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject sourceArg, AviatorObject targetArg) {
        String source = FunctionUtils.getStringValue(sourceArg, env);
        String target = FunctionUtils.getStringValue(targetArg, env);
        if(StringUtils.isEmpty(source) && StringUtils.isEmpty(target)){
            return AviatorBoolean.TRUE;
        }
        if(StringUtils.isNotEmpty(source) && StringUtils.isNotEmpty(target)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date sourceDate = sdf.parse(source);
                Date targetDate = sdf.parse(target);
                int rst = sourceDate.compareTo(targetDate);
                return rst == 0 ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else {
            return AviatorBoolean.FALSE;
        }
    }

    @Override
    public String getName() {
        return "date.eq";
    }
}