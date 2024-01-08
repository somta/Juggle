package net.somta.juggle.console.domain.expression.condition.parser;

import net.somta.juggle.core.enums.DataTypeEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author husong
 */
public class ExpressionParserFactory {

    private static Map<String,IExpressionParser> parserMap = new ConcurrentHashMap<>();

    public static IExpressionParser getParserInstance(DataTypeEnum dataType){
        IExpressionParser expressionParser = parserMap.get(dataType.name());
        if(expressionParser != null){
            return expressionParser;
        }
        if(DataTypeEnum.String.equals(dataType)){
            StringParser stringParser = new StringParser();
            parserMap.put(DataTypeEnum.String.name(),stringParser);
            return stringParser;
        }
        if(DataTypeEnum.Integer.equals(dataType)){
            IntegerParser integerParser = new IntegerParser();
            parserMap.put(DataTypeEnum.Integer.name(),integerParser);
            return integerParser;
        }
        //todo 待完善
        return null;
    }
}
