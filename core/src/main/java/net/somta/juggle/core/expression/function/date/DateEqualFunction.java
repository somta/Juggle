package net.somta.juggle.core.expression.function.date;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DateEqualFunction extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        String source = FunctionUtils.getStringValue(arg1, env);
        String target = FunctionUtils.getStringValue(arg2, env);
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
