package net.somta.juggle.core.expression.condition.parser;

import net.somta.juggle.core.enums.AssignTypeEnum;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.node.ConditionNode.ConditionExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TimeParserTest {

    @Test
    void genExpression() {
        timeEqualParserTest();
        timeNotEqualParserTest();
        timeGtParserTest();
        timeNotLtParserTest();
        timeLtParserTest();
        timeNotGtParserTest();

        IExpressionParser timeParser = new TimeParser();
        ConditionExpression unknownConditionExpression = new ConditionExpression();
        unknownConditionExpression.setEnvKey("env_birthday");
        unknownConditionExpression.setDataType(new DataType(DataTypeEnum.Time));
        unknownConditionExpression.setOperator(OperatorEnum.EMPTY.getCode());
        try {
            timeParser.genExpression(unknownConditionExpression);
        }catch (Exception e){
            Assertions.assertTrue(e instanceof IllegalArgumentException);
        }

    }

    private void timeEqualParserTest() {
        IExpressionParser timeParser = new TimeParser();
        ConditionExpression equalConditionExpression = new ConditionExpression();
        equalConditionExpression.setEnvKey("env_birthday");
        equalConditionExpression.setDataType(new DataType(DataTypeEnum.Time));
        equalConditionExpression.setOperator(OperatorEnum.EQUAL.getCode());
        equalConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        equalConditionExpression.setValue("2023-12-13 18:14:34");
        String equalString =  timeParser.genExpression(equalConditionExpression);
        Assertions.assertEquals("time.eq(env_birthday,'2023-12-13 18:14:34')",equalString);

        ConditionExpression equalConditionExpression2 = new ConditionExpression();
        equalConditionExpression2.setEnvKey("env_birthday");
        equalConditionExpression2.setDataType(new DataType(DataTypeEnum.Time));
        equalConditionExpression2.setOperator(OperatorEnum.EQUAL.getCode());
        equalConditionExpression2.setAssignType(AssignTypeEnum.VARIABLE);
        equalConditionExpression2.setValue("input_birthday");
        String equalString2 =  timeParser.genExpression(equalConditionExpression2);
        Assertions.assertEquals("time.eq(env_birthday,input_birthday)",equalString2);

    }

    private void timeNotEqualParserTest() {
        IExpressionParser timeParser = new TimeParser();
        ConditionExpression notEqualConditionExpression = new ConditionExpression();
        notEqualConditionExpression.setEnvKey("env_birthday");
        notEqualConditionExpression.setDataType(new DataType(DataTypeEnum.Time));
        notEqualConditionExpression.setOperator(OperatorEnum.NOT_EQUAL.getCode());
        notEqualConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        notEqualConditionExpression.setValue("2023-12-13 18:14:34");
        String notEqualString =  timeParser.genExpression(notEqualConditionExpression);
        Assertions.assertEquals("!time.eq(env_birthday,'2023-12-13 18:14:34')",notEqualString);
    }

    private void timeGtParserTest() {
        IExpressionParser timeParser = new TimeParser();
        ConditionExpression gtConditionExpression = new ConditionExpression();
        gtConditionExpression.setEnvKey("env_birthday");
        gtConditionExpression.setDataType(new DataType(DataTypeEnum.Time));
        gtConditionExpression.setOperator(OperatorEnum.GREATER_THAN.getCode());
        gtConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        gtConditionExpression.setValue("2023-12-13 18:14:34");
        String gtString =  timeParser.genExpression(gtConditionExpression);
        Assertions.assertEquals("time.gt(env_birthday,'2023-12-13 18:14:34')",gtString);
    }

    private void timeNotLtParserTest() {
        IExpressionParser timeParser = new TimeParser();
        ConditionExpression conditionExpression = new ConditionExpression();
        conditionExpression.setEnvKey("env_birthday");
        conditionExpression.setDataType(new DataType(DataTypeEnum.Time));
        conditionExpression.setOperator(OperatorEnum.GREATER_THAN_OR_EQUAL.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("2023-12-13 18:14:34");
        String str =  timeParser.genExpression(conditionExpression);
        Assertions.assertEquals("time.ge(env_birthday,'2023-12-13 18:14:34')",str);
    }

    private void timeLtParserTest() {
        IExpressionParser timeParser = new TimeParser();
        ConditionExpression conditionExpression = new ConditionExpression();
        conditionExpression.setEnvKey("env_birthday");
        conditionExpression.setDataType(new DataType(DataTypeEnum.Time));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("2023-12-13 18:14:34");
        String str =  timeParser.genExpression(conditionExpression);
        Assertions.assertEquals("time.lt(env_birthday,'2023-12-13 18:14:34')",str);
    }

    private void timeNotGtParserTest() {
        IExpressionParser timeParser = new TimeParser();
        ConditionExpression conditionExpression = new ConditionExpression();
        conditionExpression.setEnvKey("env_birthday");
        conditionExpression.setDataType(new DataType(DataTypeEnum.Time));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN_OR_EQUAL.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("2023-12-13 18:14:34");
        String str =  timeParser.genExpression(conditionExpression);
        Assertions.assertEquals("time.le(env_birthday,'2023-12-13 18:14:34')",str);
    }

}