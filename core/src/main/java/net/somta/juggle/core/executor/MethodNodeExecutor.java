package net.somta.juggle.core.executor;

import net.somta.common.utils.httpclient.HttpClientUtil;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.core.RuntimeContext;
import net.somta.juggle.core.enums.RequestTypeEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.BaseParameter;
import net.somta.juggle.core.model.FillStruct;
import net.somta.juggle.core.model.InputParameter;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.variable.VariableManager;
import org.apache.commons.lang3.StringUtils;

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
        try {
            Map<String,Object> parameterData =  buildParameterData(methodNode.getInputParameters(),methodNode.getInputFillRules(),runtimeContext.getVariableManager());
            Object rst = sendHttpRequest(methodNode.getUrl(),methodNode.getRequestType(),methodNode.getRequestContentType(),parameterData);
            System.out.println("接口执行完，获得的结果为：" + rst.toString());

            //从变量管理器中获取看看
            Object envName = runtimeContext.getVariableManager().getVariableValue("env_name");
            System.out.println(envName);

        } catch (FlowException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPostExecute(RuntimeContext runtimeContext) {
        System.out.println("方法节点执行器，执行后========================================");
    }

    /**
     * todo，这里只先做最简单的例子
     * 发送Http请求
     * @param url
     * @param requestType
     * @param requestContentType
     * @return
     */
    private Object sendHttpRequest(String url, RequestTypeEnum requestType, MethodNode.RequestContentType requestContentType, Map<String, Object> parameterData){
        ResponseDataResult result = HttpClientUtil.doGet(url);
        Object data = result.getResult();
        return data;
    }

    /**
     * 根据填充结构和参数描述构建带数据的参数对象
     * @return
     */
    private Map<String,Object> buildParameterData(List<InputParameter> parameters, List<FillStruct> inputFillRules, VariableManager variableManager) throws FlowException {
        Map<String,Object> paramData = new HashMap<>();
        for (InputParameter parameter : parameters){
            String fieldKey = parameter.getKey();
            // 1.先找填充结构里面有没有，没有就看默认值里面有没有，还没有就map的value赋值为空
            for(FillStruct fillStruct : inputFillRules){
                if (fieldKey.equals(fillStruct.getTarget())){
                    Object variableValue = variableManager.getVariableValue(fillStruct.getSource());
                    if(variableValue == null){
                        String defaultValue = parameter.getDefaultValue();
                        if(StringUtils.isNotEmpty(defaultValue)){
                            // todo 如果有默认值，要将值根据具体的字段的类型转换成对应的类型才行
                            paramData.put(fieldKey,defaultValue);
                        }
                    }else {
                        paramData.put(fieldKey,variableValue);
                    }
                }
            }
        }
        return paramData;
    }
}
