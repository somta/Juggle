package net.somta.juggle.console.domain.expression.parser;

import net.somta.juggle.console.domain.expression.enums.DataTypeStringEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author husong
 */
public class ExpressionParserFactory {

    private static Map<String,IExpressionParser> parserMap = new ConcurrentHashMap<>();

    public static IExpressionParser getParserInstance(String dataType){
        IExpressionParser expressionParser = parserMap.get(dataType);
        if(expressionParser != null){
            return expressionParser;
        }
        if(DataTypeStringEnum.INTEGER.getCode().equals(dataType)){
            IntegerParser integerParser = new IntegerParser();
            parserMap.put(DataTypeStringEnum.INTEGER.getCode(),integerParser);
            return integerParser;
        }
        //todo 待完善
        return null;
    }
}
