package net.somta.juggle.core.expression.condition.parser;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.AssignTypeEnum;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.node.ConditionNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleParserTest {

    @Test
    void genExpression() {
        doubleEqualParserTest();
        doubleNotEqualParserTest();
        doubleGtParserTest();
        doubleNotLtParserTest();
        doubleLtParserTest();
        doubleNotGtParserTest();
        doubleUnknownParserTest();
    }

    private void doubleEqualParserTest() {
        IExpressionParser doubleParser = new DoubleParser();
        ConditionNode.ConditionExpression notEqualConditionExpression = new ConditionNode.ConditionExpression();
        notEqualConditionExpression.setEnvKey("env_money");
        notEqualConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Double)));
        notEqualConditionExpression.setOperator(OperatorEnum.EQUAL.getCode());
        notEqualConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        notEqualConditionExpression.setValue("100.23");
        String str =  doubleParser.genExpression(notEqualConditionExpression);
        Assertions.assertEquals("env_money==100.23",str);
    }

    private void doubleNotEqualParserTest() {
        IExpressionParser doubleParser = new DoubleParser();
        ConditionNode.ConditionExpression equalConditionExpression = new ConditionNode.ConditionExpression();
        equalConditionExpression.setEnvKey("env_money");
        equalConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Double)));
        equalConditionExpression.setOperator(OperatorEnum.NOT_EQUAL.getCode());
        equalConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        equalConditionExpression.setValue("100.23");
        String str =  doubleParser.genExpression(equalConditionExpression);
        Assertions.assertEquals("env_money!=100.23",str);

    }

    private void doubleGtParserTest() {
        IExpressionParser doubleParser = new DoubleParser();
        ConditionNode.ConditionExpression gtConditionExpression = new ConditionNode.ConditionExpression();
        gtConditionExpression.setEnvKey("env_money");
        gtConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Double)));
        gtConditionExpression.setOperator(OperatorEnum.GREATER_THAN.getCode());
        gtConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        gtConditionExpression.setValue("100.23");
        String gtString =  doubleParser.genExpression(gtConditionExpression);
        Assertions.assertEquals("env_money>100.23",gtString);
    }

    private void doubleNotLtParserTest() {
        IExpressionParser doubleParser = new DoubleParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_money");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Double)));
        conditionExpression.setOperator(OperatorEnum.GREATER_THAN_OR_EQUAL.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("100.23");
        String str =  doubleParser.genExpression(conditionExpression);
        Assertions.assertEquals("env_money>=100.23",str);
    }

    private void doubleLtParserTest() {
        IExpressionParser doubleParser = new DoubleParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_money");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Double)));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("100.23");
        String str =  doubleParser.genExpression(conditionExpression);
        Assertions.assertEquals("env_money<100.23",str);
    }

    private void doubleNotGtParserTest() {
        IExpressionParser doubleParser = new DoubleParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_money");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Double)));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN_OR_EQUAL.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("100.23");
        String str =  doubleParser.genExpression(conditionExpression);
        Assertions.assertEquals("env_money<=100.23",str);
    }

    private void doubleUnknownParserTest() {
        IExpressionParser doubleParser = new DoubleParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_money");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Double)));
        conditionExpression.setOperator(OperatorEnum.CONTAINS.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("100.23");
        try {
            doubleParser.genExpression(conditionExpression);
        }catch (Exception e){
            Assertions.assertTrue(e instanceof IllegalArgumentException);
        }
    }

}