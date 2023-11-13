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
}
