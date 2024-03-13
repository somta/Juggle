package net.somta.juggle.core.expression.condition.parser;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.DataType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author husong
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
