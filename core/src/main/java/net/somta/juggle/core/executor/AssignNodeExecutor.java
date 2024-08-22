package net.somta.juggle.core.executor;

import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.enums.FieldSourceEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.FillStruct;
import net.somta.juggle.core.model.node.AssignNode;
import net.somta.juggle.core.variable.AbstractVariableManager;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static net.somta.juggle.core.enums.CoreErrorEnum.ASSIGN_NODE_ASSIGN_TYPE_ERROR;


/**
 * @author husong
 */
public class AssignNodeExecutor extends AbstractElementExecutor {
    private final static Logger logger = LoggerFactory.getLogger(AssignNodeExecutor.class);

    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {

    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        logger.debug("赋值节点执行器，执行中。。。");
        AssignNode assignNode = (AssignNode)flowRuntimeContext.getCurrentNode();
        AbstractVariableManager variableManager = flowRuntimeContext.getVariableManager();
        List<FillStruct> assignRules = assignNode.getAssignRules();
        if(CollectionUtils.isEmpty(assignRules)){
            return;
        }
        for (FillStruct fillStruct : assignRules) {
            FieldSourceEnum assignType =  fillStruct.getSourceType();
            if(FieldSourceEnum.CONSTANT == assignType){
                String envKey = fillStruct.getTarget();
                Object sourceValue = fillStruct.getSource();
                variableManager.setVariableValue(envKey,sourceValue);
            }else if(FieldSourceEnum.VARIABLE == assignType){
                String sourceEnvKey = fillStruct.getSource();
                Object sourceValue = variableManager.getVariableValue(sourceEnvKey);
                String envKey = fillStruct.getTarget();
                variableManager.setVariableValue(envKey,sourceValue);
            }else {
                new FlowException(ASSIGN_NODE_ASSIGN_TYPE_ERROR);
            }
        }
    }

    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        AssignNode assignNode = (AssignNode) flowRuntimeContext.getCurrentNode();
        String nextNodeKey = assignNode.getOutgoings().get(0);
        logger.debug("方法节点执行器完毕，下一个节点的KEY为：{}", nextNodeKey);
        super.fillNextNode(flowRuntimeContext,nextNodeKey);
    }
}
