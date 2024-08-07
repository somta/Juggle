package net.somta.juggle.core.expression.condition.parser;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.AssignTypeEnum;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.node.ConditionNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListParserTest {

    @Test
    void genExpression() {
        listEmptyParserTest();
        listNotEmptyParserTest();
        listUnknownParserTest();
    }

    private void listEmptyParserTest() {
        IExpressionParser listParser = new ListParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_userList");
        conditionExpression.setDataType(new DataType(DataTypeEnum.List));
        conditionExpression.setOperator(OperatorEnum.EMPTY.getCode());
        String str = listParser.genExpression(conditionExpression);
        Assertions.assertEquals("list.empty(env_userList)",str);
    }

    private void listNotEmptyParserTest() {
        IExpressionParser listParser = new ListParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_userList");
        conditionExpression.setDataType(new DataType(DataTypeEnum.List));
        conditionExpression.setOperator(OperatorEnum.NOT_EMPTY.getCode());
        String str = listParser.genExpression(conditionExpression);
        Assertions.assertEquals("!list.empty(env_userList)",str);
    }

    private void listUnknownParserTest() {
        IExpressionParser listParser = new ListParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_userList");
        conditionExpression.setDataType(new DataType(DataTypeEnum.List));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("100");
        try {
            listParser.genExpression(conditionExpression);
        }catch (Exception e){
            Assertions.assertTrue(e instanceof IllegalArgumentException);
        }
    }

}