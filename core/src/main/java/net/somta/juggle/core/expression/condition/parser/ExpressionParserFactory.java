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
package net.somta.juggle.core.expression.condition.parser;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.DataType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author husong
 * @since 1.0.0
 */
public class ExpressionParserFactory {

    private static Map<String,IExpressionParser> parserMap = new ConcurrentHashMap<>();

    public static IExpressionParser getParserInstance(String dataTypeStr){
        DataType dataTypeInfo = JsonSerializeHelper.deserialize(dataTypeStr, DataType.class);
        DataTypeEnum type = dataTypeInfo.getType();
        IExpressionParser expressionParser = parserMap.get(type.name());
        if(expressionParser != null){
            return expressionParser;
        }
        switch (type) {
            case String:
                expressionParser= new StringParser();
                parserMap.put(DataTypeEnum.String.name(),expressionParser);
                break;
            case Integer:
                expressionParser= new IntegerParser();
                parserMap.put(DataTypeEnum.Integer.name(),expressionParser);
                break;
            case Double:
                expressionParser= new DoubleParser();
                parserMap.put(DataTypeEnum.Double.name(),expressionParser);
                break;
            case Boolean:
                expressionParser= new BooleanParser();
                parserMap.put(DataTypeEnum.Boolean.name(),expressionParser);
                break;
            case Date:
                expressionParser= new DateParser();
                parserMap.put(DataTypeEnum.Date.name(),expressionParser);
                break;
            case List:
                expressionParser= new ListParser();
                parserMap.put(DataTypeEnum.List.name(),expressionParser);
                break;
            case Object:
                expressionParser= new ObjectParser();
                parserMap.put(DataTypeEnum.Object.name(),expressionParser);
                break;
            default:
                throw new IllegalArgumentException("目前还不支持该数据类型");
        }
        return expressionParser;
    }
}
