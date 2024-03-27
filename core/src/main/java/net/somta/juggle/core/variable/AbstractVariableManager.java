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

import net.somta.juggle.core.enums.CoreErrorEnum;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 变量管理器
 * @author husong
 * @since 1.0.0
 **/
public abstract class AbstractVariableManager {
    private Map<String, Variable> variableSchemaMap;

    public AbstractVariableManager(Map<String, Variable> variableSchemaMap) {
        this.variableSchemaMap = variableSchemaMap;
    }

    public Object getVariableValue(String key) throws FlowException{
        if(StringUtils.isEmpty(key)){
            throw new FlowException(CoreErrorEnum.ENV_KEY_ERROR);
        }
        return doGetVariableValue(key);
    }

    public Boolean setVariableValue(String key,Object value) throws FlowException {
        if(StringUtils.isEmpty(key)){
            throw new FlowException(CoreErrorEnum.ENV_KEY_ERROR);
        }
        return doSetVariableValue(key,value);
    }

    /**
     * get env real value
     * @param key env key
     * @param value env old value
     * @return env real value
     */
    public Object getRealDataType(String key,Object value) {
        Variable variable = getVariableSchema(key);
        DataType dataType = variable.getDataType();
        if(value != null){
            if(DataTypeEnum.Integer == dataType.getType()){
                value = Integer.valueOf(value.toString());
            }else if(DataTypeEnum.Double == dataType.getType()){
                value = Double.valueOf(value.toString());
            }else if(DataTypeEnum.Boolean == dataType.getType()){
                value = Boolean.valueOf(value.toString());
            }else if(DataTypeEnum.String == dataType.getType()){
                value = value.toString();
            }
            //todo 对象的真实类型如何处理待确认
        }
        return value;
    }

    protected Variable getVariableSchema(String key){
        return variableSchemaMap.get(key);
    }

    protected abstract Object doGetVariableValue(String key);

    protected abstract Boolean doSetVariableValue(String key,Object value);

}
