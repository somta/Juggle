package net.somta.juggle.core.executor;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.variable.VariableManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 判断节点执行器
 *
 * @author husong
 * @date 2023/02/06
 */
public class ConditionNodeExecutor extends ElementExecutor{

    @Override
    protected void doPreExecute(RuntimeContext runtimeContext) {
        System.out.println("判断节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(RuntimeContext runtimeContext) {
        ConditionNode conditionNode = (ConditionNode)runtimeContext.getCurrentNode();
        LinkedHashMap<String, String> conditions =  conditionNode.getConditions();
        for (String key : conditions.keySet()) {
            String expression = conditions.get(key);
            boolean result = executeExpression(expression,runtimeContext.getVariableManager());
            System.out.println("key : " + key + "-----" + "val : " + expression);
            if(result){
                return;
            }
        }
        System.out.println("判断节点执行器，执行中。。。");
    }

    @Override
    protected void doPostExecute(RuntimeContext runtimeContext) {
        System.out.println("判断节点执行器，执行后========================================");
    }


    /**
     * 执行表达式
     * @param expression 表达式
     * @param variableManager 变量管理器
     * @return
     */
    private boolean executeExpression(String expression, VariableManager variableManager) {

        //todo 校验表达式

        return false;
    }


    public static void main(String[] args) {
        // todo 怎么把表达式中的变量找出来
        String expression = "#{a}-(b-c)>100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.getInstance().compile(expression);
        Map<String, Object> env = new HashMap<>();
        env.put("#{a}", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);  // false

    }
}
