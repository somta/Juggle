package net.somta.juggle.core.executor;

import net.somta.common.utils.httpclient.HttpClientUtil;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.model.BaseParameter;
import net.somta.juggle.core.model.FillStruct;
import net.somta.juggle.core.model.InputParameter;
import net.somta.juggle.core.model.node.MethodNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO 类职责描述
 *
 * @author husong
 * @date 2023/02/06
 */
public class MethodNodeExecutor extends ElementExecutor{

    @Override
    protected void doPreExecute(RuntimeContext runtimeContext) {
        System.out.println("方法节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(RuntimeContext runtimeContext) {
        System.out.println("方法节点执行器，执行中。。。");
        MethodNode methodNode = (MethodNode)runtimeContext.getCurrentNode();
        Object rst = sendHttpRequest(methodNode.getUrl(),methodNode.getInputParameters());
        System.out.println("接口执行完，获得的结果为：" + rst.toString());
    }

    @Override
    protected void doPostExecute(RuntimeContext runtimeContext) {
        System.out.println("方法节点执行器，执行后========================================");
    }

    /**
     * todo，这里只先做最简单的例子
     * 发送Http请求
     * @param url
     * @return
     */
    private Object sendHttpRequest(String url, List<InputParameter> inputParameters){
        ResponseDataResult result = HttpClientUtil.doGet(url);
        Object data = result.getResult();
        return data;
    }

    /**
     * 根据填充结构和参数描述构建带数据的参数对象
     * @return
     */
    private Map<String,Object> buildParameterData(List<BaseParameter> parameters,List<FillStruct> inputFillRules){
        Map<String,Object> paramData = new HashMap<>();
        for (BaseParameter parameter : parameters){
            String key = parameter.getKey();
            // 1.先找填充结构里面有没有，没有就看默认值里面有没有，还没有就map的value赋值为空
        }

        return paramData;
    }
}
