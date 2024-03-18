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

class StringParserTest {

    @Test
    void genExpression() {
        stringEqualParserTest();
        stringNotEqualParserTest();
        stringEmptyParserTest();
        stringNotEmptyParserTest();
        stringContainsParserTest();
        stringNotContainsParserTest();
        stringUnknownParserTest();
    }

    private void stringEqualParserTest() {
        IExpressionParser stringParser = new StringParser();
        ConditionNode.ConditionExpression notEqualConditionExpression = new ConditionNode.ConditionExpression();
        notEqualConditionExpression.setEnvKey("env_name");
        notEqualConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        notEqualConditionExpression.setOperator(OperatorEnum.EQUAL.getCode());
        notEqualConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        notEqualConditionExpression.setValue("zhansan");
        String str = stringParser.genExpression(notEqualConditionExpression);
        Assertions.assertEquals("env_name=='zhansan'",str);
    }

    private void stringNotEqualParserTest() {
        IExpressionParser stringParser = new StringParser();
        ConditionNode.ConditionExpression equalConditionExpression = new ConditionNode.ConditionExpression();
        equalConditionExpression.setEnvKey("env_name");
        equalConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        equalConditionExpression.setOperator(OperatorEnum.NOT_EQUAL.getCode());
        equalConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        equalConditionExpression.setValue("zhansan");
        String str = stringParser.genExpression(equalConditionExpression);
        Assertions.assertEquals("env_name!='zhansan'",str);

    }

    private void stringEmptyParserTest() {
        IExpressionParser stringParser = new StringParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_name");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        conditionExpression.setOperator(OperatorEnum.EMPTY.getCode());
        String str = stringParser.genExpression(conditionExpression);
        Assertions.assertEquals("string.empty(env_name)",str);
    }

    private void stringNotEmptyParserTest() {
        IExpressionParser stringParser = new StringParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_name");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        conditionExpression.setOperator(OperatorEnum.NOT_EMPTY.getCode());
        String str = stringParser.genExpression(conditionExpression);
        Assertions.assertEquals("!string.empty(env_name)",str);
    }

    private void stringContainsParserTest() {
        IExpressionParser stringParser = new StringParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_name");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        conditionExpression.setOperator(OperatorEnum.CONTAINS.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("zhan");
        String str = stringParser.genExpression(conditionExpression);
        Assertions.assertEquals("string.contains(env_name,'zhan')",str);

        ConditionNode.ConditionExpression conditionExpression2 = new ConditionNode.ConditionExpression();
        conditionExpression2.setEnvKey("env_name");
        conditionExpression2.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        conditionExpression2.setOperator(OperatorEnum.CONTAINS.getCode());
        conditionExpression2.setAssignType(AssignTypeEnum.VARIABLE);
        conditionExpression2.setValue("zhan");
        String str2 = stringParser.genExpression(conditionExpression2);
        Assertions.assertEquals("string.contains(env_name,zhan)",str2);
    }

    private void stringNotContainsParserTest() {
        IExpressionParser stringParser = new StringParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_name");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        conditionExpression.setOperator(OperatorEnum.NOT_CONTAINS.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("zhan");
        String str = stringParser.genExpression(conditionExpression);
        Assertions.assertEquals("!string.contains(env_name,'zhan')",str);
    }

    private void stringUnknownParserTest() {
        IExpressionParser stringParser = new StringParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_name");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.String)));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("100");
        try {
            stringParser.genExpression(conditionExpression);
        }catch (Exception e){
            Assertions.assertTrue(e instanceof IllegalArgumentException);
        }
    }
}