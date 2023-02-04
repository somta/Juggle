package net.somta.juggle.core.dispatcher;

import net.somta.juggle.core.IWorkRunner;
import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.enums.FlowElementTypeEnum;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.model.node.EndNode;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.model.node.StartNode;

import java.util.*;

public abstract class AbstractDispatcher implements IDispatcher {

    /**
     * 执行work
     */
    protected IWorkRunner workRunner;

    public AbstractDispatcher(IWorkRunner workRunner) {
        this.workRunner = workRunner;
    }

    @Override
    public Boolean send(FlowDefinition flowDefinition, List<Variable> variables) {
        //1.校验流程正确性

        //2.构建流程运行的RuntimeContext
        RuntimeContext runtimeContext = buildRuntimeContext(flowDefinition);


        return doSend(runtimeContext);
    }

    /**
     * 构建流程运行的RuntimeContext
     * @return
     */
    private RuntimeContext buildRuntimeContext(FlowDefinition flowDefinition){

        RuntimeContext runtimeContext = new RuntimeContext();
        runtimeContext.setFlowStatus(FlowStatusEnum.INIT);
        runtimeContext.setFlowKey(flowDefinition.getFlowKey());
        runtimeContext.setTenantId(flowDefinition.getTenantId());
        runtimeContext.setFlowMap(buildFlowElementMap(flowDefinition.getContent()));
        return runtimeContext;
    }

    /**
     * todo 现在先mock，后面在解析
     * 构建流程元素Map
     * @param content
     * @return
     */
    private Map<String, FlowElement> buildFlowElementMap(String content){

        Map<String, FlowElement> flowElementMap = new HashMap<>();

        //开始节点
        StartNode startEventNode = new StartNode();
        startEventNode.setKey("start_2s49s");
        startEventNode.setFlowElementType(FlowElementTypeEnum.START_EVENT);
        startEventNode.setOutgoings(Arrays.asList("method_8w9r3"));
        flowElementMap.put(startEventNode.getKey(),startEventNode);


        //方法节点
        MethodNode methodNode = new MethodNode();
        methodNode.setKey("method_8w9r3");
        methodNode.setFlowElementType(FlowElementTypeEnum.METHOD);
        methodNode.setUrl("http://baidu.com");
        methodNode.setRequestType("GET");
        methodNode.setIncomings(Arrays.asList("start_2s49s"));
        methodNode.setOutgoings(Arrays.asList("end_5g463"));
        flowElementMap.put(methodNode.getKey(),methodNode);

        //判断节点
        ConditionNode coditionNode = new ConditionNode();


        //结束节点
        EndNode endEventNode = new EndNode();
        endEventNode.setKey("end_5g463");
        endEventNode.setFlowElementType(FlowElementTypeEnum.END_EVENT);
        flowElementMap.put(endEventNode.getKey(),endEventNode);

        return flowElementMap;
    }

    protected abstract Boolean doSend(RuntimeContext runtimeContext);
}
