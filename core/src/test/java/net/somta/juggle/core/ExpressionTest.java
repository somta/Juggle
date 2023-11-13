package net.somta.juggle.core;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import net.somta.juggle.core.expression.function.string.StringEmptyFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExpressionTest {

    @Test
    public void stringExpressionTest(){
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

        //等于,字符串的值要用引号引起来
        Expression compiledExp = instance.compile("env_name==\"zhansan\"");
        Map<String, Object> env = new HashMap<>();
        env.put("env_name","zhansan");
        Boolean result = (Boolean) compiledExp.execute(env);
        Assertions.assertEquals(result, true);

        //不等于,字符串的值要用引号引起来
        Expression compiledExp2 = instance.compile("env_name!='zhansan'");
        Map<String, Object> env2 = new HashMap<>();
        env2.put("env_name","lisi");
        Boolean result2 = (Boolean) compiledExp2.execute(env2);
        Assertions.assertEquals(result2, true);

        //为空
        Expression compiledExp3 = instance.compile("string.empty(env_name)");
        Map<String, Object> env3 = new HashMap<>();
        //以下两种空都支持
        env3.put("env_name",null);
        //env3.put("env_name","");
        Boolean result3 = (Boolean) compiledExp3.execute(env3);
        Assertions.assertEquals(result3, true);

        //不为空
        Expression compiledExp4 = instance.compile("!string.empty(env_name)");
        Map<String, Object> env4 = new HashMap<>();
        //env4.put("env_name",null);
        env4.put("env_name","");
        Boolean result4 = (Boolean) compiledExp4.execute(env4);
        Assertions.assertEquals(result4, false);


        //包含 s1字符串包含s2字符串
        Expression compiledExp5 = instance.compile("string.contains(s1,s2)");
        Map<String, Object> env5 = new HashMap<>();
        env5.put("s1","zhansan");
        env5.put("s2","zhan");
        Boolean result5 = (Boolean) compiledExp5.execute(env5);
        Assertions.assertEquals(result5, true);

        //包含 s1字符串包含s2字符串
        Expression compiledExp6 = instance.compile("!string.contains(s1,s2)");
        Map<String, Object> env6 = new HashMap<>();
        env6.put("s1","zhansan");
        env6.put("s2","zhan");
        Boolean result6 = (Boolean) compiledExp6.execute(env6);
        Assertions.assertEquals(result6, false);
    }

    @Test
    public void integerExpressionTest(){
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

        //等于
        Expression compiledExp = instance.compile("env_age==18");
        Map<String, Object> env = new HashMap<>();
        env.put("env_age",18);
        Boolean result = (Boolean) compiledExp.execute(env);
        Assertions.assertEquals(result, true);

        //不等于
        Expression compiledExp2 = instance.compile("env_name!=18");
        Map<String, Object> env2 = new HashMap<>();
        env2.put("env_age",30);
        Boolean result2 = (Boolean) compiledExp2.execute(env2);
        Assertions.assertEquals(result2, true);
    }

    @Test
    public void doubleExpressionTest(){
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

        //等于
        Expression compiledExp = instance.compile("env_money==100.23");
        Map<String, Object> env = new HashMap<>();
        env.put("env_money",100.23);
        Boolean result = (Boolean) compiledExp.execute(env);
        Assertions.assertEquals(result, true);

        //不等于
        Expression compiledExp2 = instance.compile("env_money!=100.23");
        Map<String, Object> env2 = new HashMap<>();
        env2.put("env_money",200.52);
        Boolean result2 = (Boolean) compiledExp2.execute(env2);
        Assertions.assertEquals(result2, true);
    }

    @Test
    public void booleanExpressionTest(){
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

        //等于
        Expression compiledExp = instance.compile("env_is_login==true");
        Map<String, Object> env = new HashMap<>();
        env.put("env_is_login",true);
        Boolean result = (Boolean) compiledExp.execute(env);
        Assertions.assertEquals(result, true);

        //不等于
        Expression compiledExp2 = instance.compile("env_is_login!=true");
        Map<String, Object> env2 = new HashMap<>();
        env2.put("env_is_login",false);
        Boolean result2 = (Boolean) compiledExp2.execute(env2);
        Assertions.assertEquals(result2, true);
    }

    @Test
    public void dateExpressionTest(){
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

        //等于
        Expression compiledExp = instance.compile("date.eq(env_birthday,'2023-12-13 18:14:34')");
        Map<String, Object> env = new HashMap<>();
        env.put("env_birthday","2023-12-13 18:14:34");
        Boolean result = (Boolean) compiledExp.execute(env);
        Assertions.assertEquals(result, true);

        //不等于
        Expression compiledExp2 = instance.compile("!date.eq(env_birthday,'2023-12-13 18:14:34')");
        Map<String, Object> env2 = new HashMap<>();
        env2.put("env_birthday","2023-12-15 18:14:34");
        Boolean result2 = (Boolean) compiledExp2.execute(env2);
        Assertions.assertEquals(result2, true);

        //大于
        Expression compiledExp3 = instance.compile("date.gt(env_birthday,'2023-12-13 18:14:34')");
        Map<String, Object> env3 = new HashMap<>();
        env3.put("env_birthday","2023-12-25 18:14:34");
        Boolean result3 = (Boolean) compiledExp3.execute(env3);
        Assertions.assertEquals(result3, true);


    }


    @Test
    public void stringExpressionTest3() throws ParseException {
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

        //等于,字符串的值要用引号引起来
       /* Expression compiledExp3 = instance.compile("string.empty(env_name)");
        Map<String, Object> env3 = new HashMap<>();
        env3.put("env_name",null);
        Boolean result3 = (Boolean) compiledExp3.execute(env3);
        Assertions.assertEquals(result3, true);

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmss");

        Date date = new Date();
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);

        System.out.println(System.currentTimeMillis());*/


        //sdf.parse("");
        Expression compiledExp2 = instance.compile("date.eq(env_birthday,'2023-12-13 18:14:34')");
        Map<String, Object> env2 = new HashMap<>();
        env2.put("env_birthday","2023-12-13 18:15:34");
        Boolean result2 = (Boolean) compiledExp2.execute(env2);
        Assertions.assertEquals(result2, true);

    }





}
