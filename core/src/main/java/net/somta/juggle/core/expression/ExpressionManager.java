package net.somta.juggle.core.expression;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.executor.ConditionNodeExecutor;
import net.somta.juggle.core.variable.BaseVariableManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author husong
 */
public class ExpressionManager {
    private final static Logger logger = LoggerFactory.getLogger(ExpressionManager.class);

    private AviatorEvaluatorInstance aviatorEvaluatorInstance = AviatorEvaluator.getInstance();
    private final BaseVariableManager variableManager;

    public ExpressionManager(BaseVariableManager variableManager) {
        this.variableManager = variableManager;
    }

    /**
     * 执行表达式
     * @param expression 表达式
     * @return
     */
    public boolean executeExpression(String expression) {
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
