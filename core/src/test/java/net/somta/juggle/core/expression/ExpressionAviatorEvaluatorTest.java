package net.somta.juggle.core.expression;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import net.somta.juggle.core.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.*;

public class ExpressionAviatorEvaluatorTest {

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


        //包含 变量s1字符串包含变量s2字符串
        Expression compiledExp5 = instance.compile("string.contains(s1,s2)");
        Map<String, Object> env5 = new HashMap<>();
        env5.put("s1","zhansan");
        env5.put("s2","zhan");
        Boolean result5 = (Boolean) compiledExp5.execute(env5);
        Assertions.assertEquals(result5, true);

        //包含 变量s1字符串包含变量s2字符串
        Expression compiledExp6 = instance.compile("!string.contains(s1,s2)");
        Map<String, Object> env6 = new HashMap<>();
        env6.put("s1","zhansan");
        env6.put("s2","zhan");
        Boolean result6 = (Boolean) compiledExp6.execute(env6);
        Assertions.assertEquals(result6, false);

        //包含 变量s1字符串包含常量zhan
        Expression compiledExp7 = instance.compile("string.contains(s1,'zhan')");
        Map<String, Object> env7 = new HashMap<>();
        env7.put("s1","zhansan");
        Boolean result7 = (Boolean) compiledExp7.execute(env7);
        Assertions.assertEquals(result7, true);

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
        Expression compiledExp2 = instance.compile("env_age!=18");
        Map<String, Object> env2 = new HashMap<>();
        env2.put("env_age",30);
        Boolean result2 = (Boolean) compiledExp2.execute(env2);
        Assertions.assertEquals(result2, true);

        //大于
        Expression compiledExp3 = instance.compile("env_age>18");
        Map<String, Object> env3 = new HashMap<>();
        env3.put("env_age",30);
        Boolean result3 = (Boolean) compiledExp3.execute(env3);
        Assertions.assertEquals(result3, true);

        //大于等于
        Expression compiledExp4 = instance.compile("env_age>=18");
        Map<String, Object> env4 = new HashMap<>();
        env4.put("env_age",30);
        Boolean result4 = (Boolean) compiledExp4.execute(env4);
        Assertions.assertEquals(result4, true);

        //小于
        Expression compiledExp5 = instance.compile("env_age<18");
        Map<String, Object> env5 = new HashMap<>();
        env5.put("env_age",10);
        Boolean result5 = (Boolean) compiledExp5.execute(env5);
        Assertions.assertEquals(result5, true);

        //小于等于
        Expression compiledExp6 = instance.compile("env_age<=18");
        Map<String, Object> env6 = new HashMap<>();
        env6.put("env_age",10);
        Boolean result6 = (Boolean) compiledExp6.execute(env6);
        Assertions.assertEquals(result6, true);

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

        //大于
        Expression compiledExp3 = instance.compile("env_money>100.23");
        Map<String, Object> env3 = new HashMap<>();
        env3.put("env_money",120.50);
        Boolean result3 = (Boolean) compiledExp3.execute(env3);
        Assertions.assertEquals(result3, true);

        //大于等于
        Expression compiledExp4 = instance.compile("env_money>=100.23");
        Map<String, Object> env4 = new HashMap<>();
        env4.put("env_money",120.50);
        Boolean result4 = (Boolean) compiledExp4.execute(env4);
        Assertions.assertEquals(result4, true);

        //小于
        Expression compiledExp5 = instance.compile("env_money<100.23");
        Map<String, Object> env5 = new HashMap<>();
        env5.put("env_money",50);
        Boolean result5 = (Boolean) compiledExp5.execute(env5);
        Assertions.assertEquals(result5, true);

        //小于等于
        Expression compiledExp6 = instance.compile("env_money<=100.23");
        Map<String, Object> env6 = new HashMap<>();
        env6.put("env_money",50);
        Boolean result6 = (Boolean) compiledExp6.execute(env6);
        Assertions.assertEquals(result6, true);
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

        //大于等于
        Expression compiledExp4 = instance.compile("date.ge(env_birthday,'2023-12-13 18:14:34')");
        Map<String, Object> env4 = new HashMap<>();
        env4.put("env_birthday","2023-12-13 18:14:34");
        //env4.put("env_birthday","2023-12-25 18:14:34");
        Boolean result4 = (Boolean) compiledExp4.execute(env4);
        Assertions.assertEquals(result4, true);

        //小于
        Expression compiledExp5 = instance.compile("date.lt(env_birthday,'2023-12-13 18:14:34')");
        Map<String, Object> env5 = new HashMap<>();
        env5.put("env_birthday","2023-9-13 18:14:34");
        Boolean result5 = (Boolean) compiledExp5.execute(env5);
        Assertions.assertEquals(result5, true);

        //小于等于
        Expression compiledExp6 = instance.compile("date.le(env_birthday,'2023-12-13 18:14:34')");
        Map<String, Object> env6 = new HashMap<>();
        env6.put("env_birthday","2023-9-13 18:14:34");
        //env6.put("env_birthday","2023-12-13 18:14:34");
        Boolean result6 = (Boolean) compiledExp6.execute(env6);
        Assertions.assertEquals(result6, true);

    }

    @Test
    public void listExpressionTest(){
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

        //为空
        Expression compiledExp = instance.compile("list.empty(env_userList)");
        Map<String, Object> env = new HashMap<>();
        List<String> userList = new ArrayList<>();
        env.put("env_userList",userList);
        //env.put("env_userList",null);
        Boolean result = (Boolean) compiledExp.execute(env);
        Assertions.assertEquals(result, true);

        //不为空
        Expression compiledExp2 = instance.compile("!list.empty(env_userList)");
        Map<String, Object> env2 = new HashMap<>();
        List<String> userList2 = new ArrayList<>();
        userList2.add("明天的地平线");
        env2.put("env_userList",userList2);
        Boolean result2 = (Boolean) compiledExp2.execute(env2);
        Assertions.assertEquals(result2, true);

    }

    @Test
    public void objectExpressionTest(){
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

        //为空
        Expression compiledExp = instance.compile("object.empty(env_student)");
        Map<String, Object> env = new HashMap<>();
        Student student = null;
        env.put("env_student",student);
        Boolean result = (Boolean) compiledExp.execute(env);
        Assertions.assertEquals(result, true);

        //不为空
        Expression compiledExp2 = instance.compile("!object.empty(env_student)");
        Map<String, Object> env2 = new HashMap<>();
        Student student2 = new Student();
        env2.put("env_student",student2);
        Boolean result2 = (Boolean) compiledExp2.execute(env2);
        Assertions.assertEquals(result2, true);

    }

    @Test
    public void logicExpressionTest(){
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

        //且
        Expression compiledExp = instance.compile("env_age==18&&string.empty(env_name)");
        Map<String, Object> env = new HashMap<>();
        env.put("env_age",18);
        env.put("env_name",null);
        Boolean result = (Boolean) compiledExp.execute(env);
        Assertions.assertEquals(result, true);

        //或 年龄等于18 或 生日小于2023-12-13
        Expression compiledExp2 = instance.compile("env_age==18||date.lt(env_birthday,'2023-12-13 18:14:34')");
        Map<String, Object> env2 = new HashMap<>();
        env2.put("env_age",20);
        env2.put("env_birthday","2023-10-13 18:14:34");
        Boolean result2 = (Boolean) compiledExp2.execute(env2);
        Assertions.assertEquals(result2, true);

        //或 年龄等于18 或 生日小于2023-12-13
        Expression compiledExp3 = instance.compile("env_money==10000&&env_age==18||date.lt(env_birthday,'2023-12-13 18:14:34')");
        Map<String, Object> env3 = new HashMap<>();
        env3.put("env_money",6000);
        env3.put("env_age",20);
        env3.put("env_birthday","2023-10-13 18:14:34");
        Boolean result3 = (Boolean) compiledExp3.execute(env3);
        Assertions.assertEquals(result3, true);

    }

    @Test
    public void ExpressionTest(){
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

        Expression compiledExp3 = instance.compile("env_money==10000&&env_age==18||date.lt(env_birthday,'2023-12-13 18:14:34')");
        Map<String, Object> env3 = new HashMap<>();
        env3.put("env_money",6000);
        env3.put("env_age",20);
        env3.put("env_birthday","2023-10-13 18:14:34");
        Boolean result3 = (Boolean) compiledExp3.execute(env3);
        Assertions.assertEquals(result3, true);

    }

    @Test
    public void ExpressionCuttingTest(){

        String expression = "env_money==10000&&env_age==18||date.lt(env_birthday,'2023-12-13 18:14:34')";
        /*List<List<ExpressionVO>> expressionList = new ArrayList<>();

        String[] orArray = expression.split("\\|\\|");
        Arrays.stream(orArray).forEach(System.out::println);

        for (String andExpression : orArray) {
            String[] andArray = andExpression.split("&&");
            Arrays.stream(andArray).forEach(System.out::println);
        }*/


    }

    @Test
    public void stringExpressionTest3() throws ParseException {
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();

    }



}
