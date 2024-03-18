package net.somta.juggle.core.expression.condition.parser;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.AssignTypeEnum;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.node.ConditionNode.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanParserTest {

    @Test
    void genExpression() {
        IExpressionParser booleanParser = new BooleanParser();
        ConditionExpression equalConditionExpression = new ConditionExpression();
        equalConditionExpression.setEnvKey("env_loginFlag");
        equalConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Boolean)));
        equalConditionExpression.setOperator(OperatorEnum.EQUAL.getCode());
        equalConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        equalConditionExpression.setValue("true");
        String equalString =  booleanParser.genExpression(equalConditionExpression);
        Assertions.assertEquals("env_loginFlag==true",equalString);

        ConditionExpression notEqualConditionExpression = new ConditionExpression();
        notEqualConditionExpression.setEnvKey("env_loginFlag");
        notEqualConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Boolean)));
        notEqualConditionExpression.setOperator(OperatorEnum.NOT_EQUAL.getCode());
        notEqualConditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        notEqualConditionExpression.setValue("true");
        String notEqualString =  booleanParser.genExpression(notEqualConditionExpression);
        Assertions.assertEquals("env_loginFlag!=true",notEqualString);

        ConditionExpression unknownConditionExpression = new ConditionExpression();
        unknownConditionExpression.setEnvKey("env_loginFlag");
        unknownConditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Boolean)));
        unknownConditionExpression.setOperator(OperatorEnum.EMPTY.getCode());
        try {
            booleanParser.genExpression(unknownConditionExpression);
        }catch (Exception e){
            Assertions.assertTrue(e instanceof IllegalArgumentException);
        }

    }
}