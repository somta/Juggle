/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.core.executor;

import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.enums.FieldSourceEnum;
import net.somta.juggle.core.enums.RequestContentTypeEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.http.HttpClientFactory;
import net.somta.juggle.core.http.IHttpClient;
import net.somta.juggle.core.http.Request;
import net.somta.juggle.core.model.*;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.variable.AbstractVariableManager;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法节点执行器
 * @author husong
 * @since 1.0.0
 */
public class MethodNodeExecutor extends AbstractElementExecutor {
    private final static Logger logger = LoggerFactory.getLogger(MethodNodeExecutor.class);

    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {
        logger.debug("方法节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        logger.debug("方法节点执行器，执行中。。。");
        MethodNode methodNode = (MethodNode) flowRuntimeContext.getCurrentNode();
        try {
            Map<String,Object> parameterData =  buildInputParameterData(methodNode.getMethod().getInputFillRules(), flowRuntimeContext.getVariableManager());
            Map<String,Object> headerData = buildHeaderData(methodNode.getMethod().getHeaderFillRules(), flowRuntimeContext.getVariableManager());
            Map<String,Object> resultData = sendHttpRequest(methodNode.getMethod(), headerData, parameterData);
            logger.debug("接口执行完，获得的结果为：" + resultData.toString());

            buildOutputParameterData(methodNode.getMethod(), flowRuntimeContext.getVariableManager(),resultData);
        } catch (FlowException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        MethodNode methodNode = (MethodNode) flowRuntimeContext.getCurrentNode();
        String nextNodeKey = methodNode.getOutgoings().get(0);
        logger.debug("方法节点执行器完毕，下一个节点的KEY为：{}", nextNodeKey);
        super.fillNextNode(flowRuntimeContext,nextNodeKey);
    }

    /**
     * 发送Http请求
     * @param method
     * @param parameterData
     * @return
     */
    private Map<String,Object> sendHttpRequest(Method method, Map<String, Object> headerData, Map<String, Object> parameterData){
        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.findEnumByValue(method.getRequestContentType()));
        Request request = new Request(method.getRequestType(),method.getInputParamSchemas());
        request.initRequest(method.getUrl(),headerData,parameterData);
        //request.setRequestHeaders(headerData);
        //request.setRequestParams(parameterData);
        //todo  这里参考接口debug完善 这个参数位置到底放那个对象里面呢
        Map<String,Object> result = httpClient.sendRequest(request);
        return result;
    }

    private Map<String, Object> buildHeaderData(List<FillStruct> headerFillRules, AbstractVariableManager variableManager) throws FlowException {
        if(CollectionUtils.isEmpty(headerFillRules)){
            return Collections.EMPTY_MAP;
        }
        Map<String,Object> headerData = new HashMap<>(headerFillRules.size());
        for(FillStruct fillStruct : headerFillRules){
            String fieldKey = fillStruct.getTarget();
            if(FieldSourceEnum.CONSTANT.equals(fillStruct.getSourceType())){
                headerData.put(fieldKey,fillStruct.getSource());
            } else {
                Object variableValue = variableManager.getVariableValue(fillStruct.getSource());
                headerData.put(fieldKey,variableValue);
            }
        }
        return headerData;
    }

    /**
     * 根据填充结构和参数描述构建带数据的参数对象
     * @return
     */
    private Map<String,Object> buildInputParameterData(List<FillStruct> inputFillRules, AbstractVariableManager variableManager) throws FlowException {
        if(CollectionUtils.isEmpty(inputFillRules)){
            return Collections.EMPTY_MAP;
        }
        Map<String,Object> paramData = new HashMap<>(8);
        // todo 如果是这种类型，怎么支持高级类型，user.id这样的结构，看看bizw是怎么做的
        for(FillStruct fillStruct : inputFillRules){
            String fieldKey = fillStruct.getTarget();
            if(FieldSourceEnum.CONSTANT.equals(fillStruct.getSourceType())){
                paramData.put(fieldKey,fillStruct.getSource());
            }else {
                Object variableValue = variableManager.getVariableValue(fillStruct.getSource());
                paramData.put(fieldKey,variableValue);
            }
        }
        return paramData;
    }

    /**
     * 处理填充出参数据
     * @param method
     * @param variableManager
     */
    private void buildOutputParameterData(Method method, AbstractVariableManager variableManager, Map<String,Object> resultData) throws FlowException {
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
}
