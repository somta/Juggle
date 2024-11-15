package net.somta.juggle.core.expression;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.AssignTypeEnum;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.expression.condition.enums.OperatorEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.node.ConditionNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionManagerTest {

    @Test
    void executeExpression() {
    }

    @Test
    void generateExpression() {
        List<List<ConditionNode.ConditionExpression>> expressionList = new ArrayList<>();
        //第一个且表达式集合
        List<ConditionNode.ConditionExpression> expressionList1 = new ArrayList<>();
        ConditionNode.ConditionExpression expressionVo1 = new ConditionNode.ConditionExpression();
        expressionVo1.setEnvKey("env_userName");
        expressionVo1.setDataType(new DataType(DataTypeEnum.String));
        expressionVo1.setOperator(OperatorEnum.EQUAL.getCode());
        expressionVo1.setAssignType(AssignTypeEnum.CONSTANT);
        expressionVo1.setValue("zhansan");
        expressionList1.add(expressionVo1);

        ConditionNode.ConditionExpression expressionVo2 = new ConditionNode.ConditionExpression();
        expressionVo2.setEnvKey("env_age");
        expressionVo2.setDataType(new DataType(DataTypeEnum.Integer));
        expressionVo2.setOperator(OperatorEnum.EQUAL.getCode());
        expressionVo2.setAssignType(AssignTypeEnum.CONSTANT);
        expressionVo2.setValue("18");
        expressionList1.add(expressionVo2);

        expressionList.add(expressionList1);

        //第二个且表达式集合
        List<ConditionNode.ConditionExpression> expressionList2 = new ArrayList<>();
        ConditionNode.ConditionExpression expressionVo3 = new ConditionNode.ConditionExpression();
        expressionVo3.setEnvKey("env_userName");
        expressionVo3.setDataType(new DataType(DataTypeEnum.String));
        expressionVo3.setOperator(OperatorEnum.CONTAINS.getCode());
        expressionVo3.setAssignType(AssignTypeEnum.CONSTANT);
        expressionVo3.setValue("san");
        expressionList2.add(expressionVo3);

        expressionList.add(expressionList2);

        try {
            String str = JsonSerializeHelper.serialize(expressionList);
            System.out.println(str);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        String expression = ExpressionManager.generateExpression(expressionList);
        System.out.println(expression);
    }
}