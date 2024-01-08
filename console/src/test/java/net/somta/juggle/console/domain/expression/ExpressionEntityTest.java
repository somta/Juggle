package net.somta.juggle.console.domain.expression;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.expression.condition.ConditionExpressionEntity;
import net.somta.juggle.console.domain.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.node.ConditionNode.ConditionExpression;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ExpressionEntityTest {

    @Test
    public void generateExpressionTest(){
        List<List<ConditionExpression>> expressionList = new ArrayList<>();
        //第一个且表达式集合
        List<ConditionExpression> expressionList1 = new ArrayList<>();
        ConditionExpression expressionVo1 = new ConditionExpression();
        expressionVo1.setEnvKey("env_userName");
        expressionVo1.setDataType(DataTypeEnum.String);
        expressionVo1.setOperator(OperatorEnum.EQUAL.getCode());
        expressionVo1.setAssignType("constant");
        expressionVo1.setValue("zhansan");
        expressionList1.add(expressionVo1);

        ConditionExpression expressionVo2 = new ConditionExpression();
        expressionVo2.setEnvKey("env_age");
        expressionVo2.setDataType(DataTypeEnum.Integer);
        expressionVo2.setOperator(OperatorEnum.EQUAL.getCode());
        expressionVo2.setAssignType("constant");
        expressionVo2.setValue("18");
        expressionList1.add(expressionVo2);

        expressionList.add(expressionList1);

        //第二个且表达式集合
        List<ConditionExpression> expressionList2 = new ArrayList<>();
        ConditionExpression expressionVo3 = new ConditionExpression();
        expressionVo3.setEnvKey("env_userName");
        expressionVo3.setDataType(DataTypeEnum.String);
        expressionVo3.setOperator(OperatorEnum.CONTAINS.getCode());
        expressionVo3.setAssignType("constant");
        expressionVo3.setValue("san");
        expressionList2.add(expressionVo3);

        expressionList.add(expressionList2);

        String str = JsonSerializeHelper.serialize(expressionList);
        System.out.println(str);

        String expression = ConditionExpressionEntity.generateExpression(expressionList);
        System.out.println(expression);
    }

}