package net.somta.juggle.core.executor;

import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.*;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.utils.HttpClient;
import net.somta.juggle.core.variable.BaseVariableManager;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法节点执行器
 *
 * @author husong
 * @date 2023/02/06
 */
public class MethodNodeExecutor extends AbstractElementExecutor {

    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("方法节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("方法节点执行器，执行中。。。");
        MethodNode methodNode = (MethodNode) flowRuntimeContext.getCurrentNode();
        try {
            Map<String,Object> parameterData =  buildInputParameterData(methodNode.getMethod().getInputFillRules(), flowRuntimeContext.getVariableManager());
            Map<String,Object> resultData = sendHttpRequest(methodNode.getMethod(),parameterData);
            System.out.println("接口执行完，获得的结果为：" + resultData.toString());

            buildOutputParameterData(methodNode.getMethod(), flowRuntimeContext.getVariableManager(),resultData);

            //从变量管理器中获取看看
            Object envName = flowRuntimeContext.getVariableManager().getVariableValue("env_name");
            System.out.println(envName);

        } catch (FlowException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        System.out.println("方法节点执行器，执行后========================================");
    }

    /**
     * todo，这里只先做最简单的例子
     * 发送Http请求
     * @param method
     * @param parameterData
     * @return
     */
    private Map<String,Object> sendHttpRequest(Method method, Map<String, Object> parameterData){
        HttpClient httpClient = new HttpClient();
        HttpClient.Request request = new HttpClient.Request(method.getRequestType(),method.getUrl());
        Map<String,Object> result = httpClient.sendRequest(request);
        return result;
    }

    /**
     * 根据填充结构和参数描述构建带数据的参数对象
     * @return
     */
    private Map<String,Object> buildInputParameterData(List<FillStruct> inputFillRules, BaseVariableManager variableManager) throws FlowException {
        if(CollectionUtils.isEmpty(inputFillRules)){
            return Collections.EMPTY_MAP;
        }
        Map<String,Object> paramData = new HashMap<>(8);
        // todo 如果是这种类型，怎么支持高级类型，user.id这样的结构，看看bizw是怎么做的
        for(FillStruct fillStruct : inputFillRules){
            String fieldKey = fillStruct.getTarget();
            Object variableValue = variableManager.getVariableValue(fillStruct.getSource());
            paramData.put(fieldKey,variableValue);
        }
        return paramData;
    }

    /**
     * 根据填充结构和参数描述构建带数据的参数对象
     * @return
     */
/*    private Map<String,Object> buildInputParameterData2(List<InputParameter> parameters, List<FillStruct> inputFillRules, VariableManager variableManager) throws FlowException {
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
    }*/

    /**
     * 处理填充出参数据
     * @param method
     * @param variableManager
     */
    private void buildOutputParameterData(Method method, BaseVariableManager variableManager, Map<String,Object> resultData) throws FlowException {
        List<FillStruct> outputFillRules = method.getOutputFillRules();
        if(CollectionUtils.isEmpty(outputFillRules)){
            return;
        }
        for (FillStruct fillStruct : outputFillRules) {
            String fieldKey = fillStruct.getSource();
            Object fieldValue = resultData.get(fieldKey);

            //设置变量
            String envKey = fillStruct.getTarget();
            variableManager.setVariableValue(envKey,fieldValue);
        }
    }

    /**
     * 处理填充出参数据
     * @param method
     * @param variableManager
     */
    /*private void buildOutputParameterData2(Method method,VariableManager variableManager, Map<String,Object> resultData) throws FlowException {
        List<OutputParameter> outputParameters = method.getOutputParameters();
        List<FillStruct> outputFillRules = method.getOutputFillRules();
        if(CollectionUtils.isEmpty(outputFillRules)){
            return;
        }
        for (FillStruct fillStruct : outputFillRules) {
            String fieldKey = fillStruct.getSource();
            Object fieldValue = resultData.get(fieldKey);

            //设置变量
            String envKey = fillStruct.getTarget();
            variableManager.setVariableValue(envKey,fieldValue);
        }
    }*/
}
