package net.somta.juggle.core.expression.condition.parser;

import net.somta.juggle.core.enums.AssignTypeEnum;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.node.ConditionNode.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DateParserTest {

    @Test
    void genExpression() {
        dateEqualParserTest();
        dateNotEqualParserTest();
        dateGtParserTest();
        dateNotLtParserTest();
        dateLtParserTest();
        dateNotGtParserTest();

        IExpressionParser dateParser = new DateParser();
        ConditionExpression unknownConditionExpression = new ConditionExpression();
        unknownConditionExpression.setEnvKey("env_birthday");
        unknownConditionExpression.setDataType(new DataType(DataTypeEnum.Date));
        unknownConditionExpression.setOperator(OperatorEnum.EMPTY.getCode());
        try {
            dateParser.genExpression(unknownConditionExpression);
        }catch (Exception e){
            Assertions.assertTrue(e instanceof IllegalArgumentException);
        }

    }

    private void dateEqualParserTest() {
        IExpressionParser dateParser = new DateParser();
        ConditionExpression equalConditionExpression = new ConditionExpression();
        equalConditionExpression.setEnvKey("env_birthday");
        equalConditionExpression.setDataType(new DataType(DataTypeEnum.Date));
        equalConditionExpression.setOperator(OperatorEnum.EQUAL.getCode());
        equalConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        equalConditionExpression.setValue("2023-12-13");
        String equalString =  dateParser.genExpression(equalConditionExpression);
        Assertions.assertEquals("date.eq(env_birthday,'2023-12-13')",equalString);

        ConditionExpression equalConditionExpression2 = new ConditionExpression();
        equalConditionExpression2.setEnvKey("env_birthday");
        equalConditionExpression2.setDataType(new DataType(DataTypeEnum.Date));
        equalConditionExpression2.setOperator(OperatorEnum.EQUAL.getCode());
        equalConditionExpression2.setAssignType(AssignTypeEnum.VARIABLE);
        equalConditionExpression2.setValue("input_birthday");
        String equalString2 =  dateParser.genExpression(equalConditionExpression2);
        Assertions.assertEquals("date.eq(env_birthday,input_birthday)",equalString2);

    }

    private void dateNotEqualParserTest() {
        IExpressionParser dateParser = new DateParser();
        ConditionExpression notEqualConditionExpression = new ConditionExpression();
        notEqualConditionExpression.setEnvKey("env_birthday");
        notEqualConditionExpression.setDataType(new DataType(DataTypeEnum.Date));
        notEqualConditionExpression.setOperator(OperatorEnum.NOT_EQUAL.getCode());
        notEqualConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        notEqualConditionExpression.setValue("2023-12-13");
        String notEqualString =  dateParser.genExpression(notEqualConditionExpression);
        Assertions.assertEquals("!date.eq(env_birthday,'2023-12-13')",notEqualString);
    }

    private void dateGtParserTest() {
        IExpressionParser dateParser = new DateParser();
        ConditionExpression gtConditionExpression = new ConditionExpression();
        gtConditionExpression.setEnvKey("env_birthday");
        gtConditionExpression.setDataType(new DataType(DataTypeEnum.Date));
        gtConditionExpression.setOperator(OperatorEnum.GREATER_THAN.getCode());
        gtConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        gtConditionExpression.setValue("2023-12-13");
        String gtString =  dateParser.genExpression(gtConditionExpression);
        Assertions.assertEquals("date.gt(env_birthday,'2023-12-13')",gtString);
    }

    private void dateNotLtParserTest() {
        IExpressionParser dateParser = new DateParser();
        ConditionExpression conditionExpression = new ConditionExpression();
        conditionExpression.setEnvKey("env_birthday");
        conditionExpression.setDataType(new DataType(DataTypeEnum.Date));
        conditionExpression.setOperator(OperatorEnum.GREATER_THAN_OR_EQUAL.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("2023-12-13");
        String str =  dateParser.genExpression(conditionExpression);
        Assertions.assertEquals("date.ge(env_birthday,'2023-12-13')",str);
    }

    private void dateLtParserTest() {
        IExpressionParser dateParser = new DateParser();
        ConditionExpression conditionExpression = new ConditionExpression();
        conditionExpression.setEnvKey("env_birthday");
        conditionExpression.setDataType(new DataType(DataTypeEnum.Date));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("2023-12-13");
        String str =  dateParser.genExpression(conditionExpression);
        Assertions.assertEquals("date.lt(env_birthday,'2023-12-13')",str);
    }

    private void dateNotGtParserTest() {
        IExpressionParser dateParser = new DateParser();
        ConditionExpression conditionExpression = new ConditionExpression();
        conditionExpression.setEnvKey("env_birthday");
        conditionExpression.setDataType(new DataType(DataTypeEnum.Date));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN_OR_EQUAL.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("2023-12-13");
        String str =  dateParser.genExpression(conditionExpression);
        Assertions.assertEquals("date.le(env_birthday,'2023-12-13')",str);
    }

}