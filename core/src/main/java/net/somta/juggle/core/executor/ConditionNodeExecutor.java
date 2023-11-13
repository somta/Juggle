package net.somta.juggle.core.executor;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.variable.BaseVariableManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 判断节点执行器
 *
 * @author husong
 * @date 2023/02/06
 */
public class ConditionNodeExecutor extends AbstractElementExecutor {
    private final static Logger logger = LoggerFactory.getLogger(ConditionNodeExecutor.class);

    private AviatorEvaluatorInstance aviatorEvaluatorInstance = AviatorEvaluator.getInstance();

    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("判断节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        ConditionNode conditionNode = (ConditionNode) flowRuntimeContext.getCurrentNode();
        List<ConditionNode.ConditionItem> conditions =  conditionNode.getConditions();
        String hitOutGoingKey = null;
        for (ConditionNode.ConditionItem conditionItem : conditions) {
            String expression = conditionItem.getExpression();
            boolean result = executeExpression(expression, flowRuntimeContext.getVariableManager());
            if(result){
                hitOutGoingKey = conditionItem.getOutgoing();
                break;
            }
        }
        flowRuntimeContext.setCurrentNode(flowRuntimeContext.getFlowElementMap().get(hitOutGoingKey));
        System.out.println("判断节点执行器，执行中。。。");
    }

    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("判断节点执行器，执行后========================================");
    }


    /**
     * 执行表达式
     * @param expression 表达式
     * @param variableManager 变量管理器
     * @return
     */
    private boolean executeExpression(String expression, BaseVariableManager variableManager) {
        if(StringUtils.isEmpty(expression)){
            return true;
        }
        Expression compiledExp = aviatorEvaluatorInstance.compile(expression);
        List<String> variableKeys = compiledExp.getVariableNames();
        Map<String, Object> env = new HashMap<>(8);
        for (String key : variableKeys){
            try {
                env.put(key,variableManager.getVariableValue(key));
            } catch (FlowException e) {
                e.printStackTrace();
            }
        }
        // 执行表达式
        boolean result = (Boolean) compiledExp.execute(env);
        logger.debug("expression:{},env:{},result",expression,env,result);
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
