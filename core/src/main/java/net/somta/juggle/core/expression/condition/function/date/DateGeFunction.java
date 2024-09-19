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
 * 大于等于
 * @author husong
 * @since 1.0.0
 */
public class DateGeFunction extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject sourceArg, AviatorObject targetArg) {
        String source = FunctionUtils.getStringValue(sourceArg, env);
        String target = FunctionUtils.getStringValue(targetArg, env);
        if(StringUtils.isEmpty(source) || StringUtils.isEmpty(target)){
            return AviatorBoolean.FALSE;
        }
        if(StringUtils.isNotEmpty(source) && StringUtils.isNotEmpty(target)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date sourceDate = sdf.parse(source);
                Date targetDate = sdf.parse(target);
                boolean rst = sourceDate.after(targetDate) || sourceDate.compareTo(targetDate) == 0;
                return rst ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else {
            return AviatorBoolean.FALSE;
        }
    }

    @Override
    public String getName() {
        return "date.ge";
    }

}
