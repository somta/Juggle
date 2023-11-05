package net.somta.juggle.core;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

public class ExpressionTest {


    // todo 这里单侧没有生效
    //@Test
    public void stringExpressionTest(){
        Expression compiledExp = AviatorEvaluator.getInstance().compile("env_name=='zhansan'");
        Map<String, Object> env = new HashMap<>();
        env.put("env_name","zhansan");
        Boolean result = (Boolean) compiledExp.execute(env);
        //Assertions.assertEquals(result, true);
        System.out.println(result);
    }

    public static void main(String[] args) {
        ExpressionTest expressionTest = new ExpressionTest();
        expressionTest.stringExpressionTest();

    }

}
