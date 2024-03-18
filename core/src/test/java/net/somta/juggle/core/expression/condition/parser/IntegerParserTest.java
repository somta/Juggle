package net.somta.juggle.core.expression.condition.parser;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.AssignTypeEnum;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.node.ConditionNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntegerParserTest {

    @Test
    void genExpression() {
        integerEqualParserTest();
        integerNotEqualParserTest();
        integerGtParserTest();
        integerNotLtParserTest();
        integerLtParserTest();
        integerNotGtParserTest();
        integerUnknownParserTest();
    }

    private void integerEqualParserTest() {
        IExpressionParser integerParser = new IntegerParser();
        ConditionNode.ConditionExpression notEqualConditionExpression = new ConditionNode.ConditionExpression();
        notEqualConditionExpression.setEnvKey("env_age");
        notEqualConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        notEqualConditionExpression.setOperator(OperatorEnum.EQUAL.getCode());
        notEqualConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        notEqualConditionExpression.setValue("18");
        String str =  integerParser.genExpression(notEqualConditionExpression);
        Assertions.assertEquals("env_age==18",str);
    }

    private void integerNotEqualParserTest() {
        IExpressionParser integerParser = new IntegerParser();
        ConditionNode.ConditionExpression equalConditionExpression = new ConditionNode.ConditionExpression();
        equalConditionExpression.setEnvKey("env_age");
        equalConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        equalConditionExpression.setOperator(OperatorEnum.NOT_EQUAL.getCode());
        equalConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        equalConditionExpression.setValue("18");
        String str =  integerParser.genExpression(equalConditionExpression);
        Assertions.assertEquals("env_age!=18",str);

    }

    private void integerGtParserTest() {
        IExpressionParser integerParser = new IntegerParser();
        ConditionNode.ConditionExpression gtConditionExpression = new ConditionNode.ConditionExpression();
        gtConditionExpression.setEnvKey("env_age");
        gtConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        gtConditionExpression.setOperator(OperatorEnum.GREATER_THAN.getCode());
        gtConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        gtConditionExpression.setValue("18");
        String gtString =  integerParser.genExpression(gtConditionExpression);
        Assertions.assertEquals("env_age>18",gtString);
    }

    private void integerNotLtParserTest() {
        IExpressionParser integerParser = new IntegerParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_age");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        conditionExpression.setOperator(OperatorEnum.GREATER_THAN_OR_EQUAL.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("18");
        String str =  integerParser.genExpression(conditionExpression);
        Assertions.assertEquals("env_age>=18",str);
    }

    private void integerLtParserTest() {
        IExpressionParser integerParser = new IntegerParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_age");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("18");
        String str =  integerParser.genExpression(conditionExpression);
        Assertions.assertEquals("env_age<18",str);
    }

    private void integerNotGtParserTest() {
        IExpressionParser integerParser = new IntegerParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_age");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN_OR_EQUAL.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("18");
        String str =  integerParser.genExpression(conditionExpression);
        Assertions.assertEquals("env_age<=18",str);
    }

    private void integerUnknownParserTest() {
        IExpressionParser integerParser = new IntegerParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_age");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Integer)));
        conditionExpression.setOperator(OperatorEnum.CONTAINS.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("100.23");
        try {
            integerParser.genExpression(conditionExpression);
        }catch (Exception e){
            Assertions.assertTrue(e instanceof IllegalArgumentException);
        }
    }
}