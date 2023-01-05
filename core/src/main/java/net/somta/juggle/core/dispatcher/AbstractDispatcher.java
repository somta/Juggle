package net.somta.juggle.core.dispatcher;

import net.somta.juggle.core.IWorkRunner;
import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.model.WorkflowDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractDispatcher implements IDispatcher {

    /**
     * 执行work
     */
    protected IWorkRunner workRunner;

    public AbstractDispatcher(IWorkRunner workRunner) {
        this.workRunner = workRunner;
    }

    @Override
    public Boolean send(WorkflowDefinition workflowDefinition, List<Variable> variables) {
        //1.校验流程正确性

        //2.构建流程运行的RuntimeContext
        RuntimeContext runtimeContext = buildRuntimeContext();


        return doSend(runtimeContext);
    }

    /**
     *
     * @return
     */
    RuntimeContext buildRuntimeContext(){

        return null;
    }

    protected abstract Boolean doSend(RuntimeContext runtimeContext);
}
