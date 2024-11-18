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

package net.somta.juggle.core.variable;

import com.jayway.jsonpath.JsonPath;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于内存的变量管 理器
 * @author husong
 * @since 1.0.0
 **/
public class MemoryVariableManager extends AbstractVariableManager {

    private final Map<String,Object> variableValueMap;

    public MemoryVariableManager(Map<String, Variable> variableSchemaMap) {
        super(variableSchemaMap);
        this.variableValueMap = new HashMap<>();
    }

    @Override
    protected Object doGetVariableValue(String key) {
        Object value = null;
        if(key.contains(".")){;
         String path = "$." + key;
         value = JsonPath.read(variableValueMap,path);
         Object v = JsonPath.read(variableValueMap,path);
         System.out.println(v);
        }else{
            value = variableValueMap.get(key);
        }
        return value;
    }

    @Override
    protected Boolean doSetVariableValue(String key, Object value) {
        Object realValue = getRealDataType(key,value);
        variableValueMap.put(key,realValue);
        return true;
    }
}
