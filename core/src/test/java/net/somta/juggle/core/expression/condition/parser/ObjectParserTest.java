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

class ObjectParserTest {

    @Test
    void genExpression() {
        objectEmptyParserTest();
        objectNotEmptyParserTest();
        objectUnknownParserTest();
    }

    private void objectEmptyParserTest() {
        IExpressionParser objectParser = new ObjectParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_user");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Object)));
        conditionExpression.setOperator(OperatorEnum.EMPTY.getCode());
        String str = objectParser.genExpression(conditionExpression);
        Assertions.assertEquals("object.empty(env_user)",str);
    }

    private void objectNotEmptyParserTest() {
        IExpressionParser objectParser = new ObjectParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_user");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Object)));
        conditionExpression.setOperator(OperatorEnum.NOT_EMPTY.getCode());
        String str = objectParser.genExpression(conditionExpression);
        Assertions.assertEquals("!object.empty(env_user)",str);
    }

    private void objectUnknownParserTest() {
        IExpressionParser objectParser = new ObjectParser();
        ConditionNode.ConditionExpression conditionExpression = new ConditionNode.ConditionExpression();
        conditionExpression.setEnvKey("env_user");
        conditionExpression.setDataType(JsonSerializeHelper.serialize(new DataType(DataTypeEnum.Object)));
        conditionExpression.setOperator(OperatorEnum.LESS_THAN.getCode());
        conditionExpression.setAssignType(AssignTypeEnum.CONSTANT);
        conditionExpression.setValue("100");
        try {
            objectParser.genExpression(conditionExpression);
        }catch (Exception e){
            Assertions.assertTrue(e instanceof IllegalArgumentException);
        }
    }
}