package net.somta.juggle.core.executor;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.variable.VariableManager;

import java.util.*;

/**
 * 判断节点执行器
 *
 * @author husong
 * @date 2023/02/06
 */
public class ConditionNodeExecutor extends ElementExecutor{

    private AviatorEvaluatorInstance aviatorEvaluatorInstance = AviatorEvaluator.getInstance();

    @Override
    protected void doPreExecute(RuntimeContext runtimeContext) {
        System.out.println("判断节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(RuntimeContext runtimeContext) {
        ConditionNode conditionNode = (ConditionNode)runtimeContext.getCurrentNode();
        List<ConditionNode.ConditionItem> conditions =  conditionNode.getConditions();
        String hitOutGoingKey = null;
        for (ConditionNode.ConditionItem conditionItem : conditions) {
            String expression = conditionItem.getExpression();
            boolean result = executeExpression(expression,runtimeContext.getVariableManager());
            System.out.println("key : " + conditionItem.getOutgoing() + "-----" + "val : " + expression + "result" + result);
            if(result){
                hitOutGoingKey = conditionItem.getOutgoing();
                break;
            }
        }
        runtimeContext.setCurrentNode(runtimeContext.getFlowElementMap().get(hitOutGoingKey));
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
        Expression compiledExp = aviatorEvaluatorInstance.compile(expression);
        List<String> variableKeys = compiledExp.getVariableNames();
        Map<String, Object> env = new HashMap<>();
        for (String key : variableKeys){
            try {
                env.put(key,variableManager.getVariableValue(key));
            } catch (FlowException e) {
                e.printStackTrace();
            }
        }
        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        return result;
    }


    /*public static void main(String[] args) {
        // todo 怎么把表达式中的变量找出来
        String expression = "a-(b-c)>100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.getInstance().compile(expression);
        // 获取所有的变量
        List<String> vars1 = compiledExp.getVariableNames();
        System.out.println(vars1);
        Map<String, Object> env = new HashMap<>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);  // false


        Expression exp = AviatorEvaluator
                .compile("b=2; if(a > 1) { a + b } elsif( a > 10) { return a + c; } else { return 10; }");
        List<String> vars = exp.getVariableNames();
        System.out.println(vars);

    }*/
}
