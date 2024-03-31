package net.somta.juggle.core.executor;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationOutput;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.MessageManager;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.model.node.CodeNode;
import net.somta.juggle.core.model.node.OpenAiNode;
import net.somta.juggle.core.variable.AbstractVariableManager;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author husong
 */
public class OpenAiNodeExecutor extends AbstractElementExecutor {
    private final static Logger logger = LoggerFactory.getLogger(OpenAiNodeExecutor.class);

    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {

    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        logger.debug("OpenAi节点执行器，执行中。。。");
        OpenAiNode openAiNode = (OpenAiNode) flowRuntimeContext.getCurrentNode();
        Generation gen = new Generation();
        MessageManager msgManager = new MessageManager(10);
        Message systemMsg = Message.builder().role(Role.SYSTEM.getValue()).content(openAiNode.getPrompt()).build();
        Message userMsg = Message.builder().role(Role.USER.getValue()).content(openAiNode.getContent()).build();
        msgManager.add(systemMsg);
        msgManager.add(userMsg);

        GenerationParam param = GenerationParam.builder()
                .apiKey(openAiNode.getApiKey())
                .model(Generation.Models.QWEN_TURBO)
                .messages(msgManager.get())
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        String resultMsg = null;
        try {
            GenerationResult result = gen.call(param);
            List<GenerationOutput.Choice> choices = result.getOutput().getChoices();
            if(CollectionUtils.isNotEmpty(choices)){
                resultMsg = choices.get(0).getMessage().getContent();
            }
        } catch (NoApiKeyException e) {
            e.printStackTrace();
        } catch (InputRequiredException e) {
            e.printStackTrace();
        }

        String targetVariableKey = openAiNode.getResultFillRules().getTarget();
        AbstractVariableManager variableManager = flowRuntimeContext.getVariableManager();
        variableManager.setVariableValue(targetVariableKey,resultMsg);
    }

    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        OpenAiNode openAiNode = (OpenAiNode) flowRuntimeContext.getCurrentNode();
        String nextNodeKey = openAiNode.getOutgoings().get(0);
        logger.debug("方法节点执行器完毕，下一个节点的KEY为：{}", nextNodeKey);
        super.fillNextNode(flowRuntimeContext,nextNodeKey);
    }
}
