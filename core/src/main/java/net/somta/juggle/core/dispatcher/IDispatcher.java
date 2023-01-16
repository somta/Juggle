package net.somta.juggle.core.dispatcher;

import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.model.WorkflowDefinition;

import java.util.List;

/**
 * 调度器接口
 */
public interface IDispatcher {

    /**
     * 分发请求
     * @return
     */
    Boolean send(WorkflowDefinition workflowDefinition, List<Variable> variables);

}
