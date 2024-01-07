package net.somta.juggle.console.domain.expression;

import net.somta.juggle.console.domain.expression.vo.ExpressionVO;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author husong
 */
public class ExpressionEntity {

    public static String generateExpression(List<List<ExpressionVO>> expressionList){
        if(CollectionUtils.isEmpty(expressionList)){
            return null;
        }
        List<String> expressions = new ArrayList<>();
        return null;
    }
}
