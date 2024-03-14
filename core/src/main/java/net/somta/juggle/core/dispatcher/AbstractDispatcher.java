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

package net.somta.juggle.core.dispatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.juggle.core.IWorkRunner;
import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.FlowResult;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.result.IFlowResultManager;
import net.somta.juggle.core.variable.AbstractVariableManager;
import org.apache.commons.collections4.MapUtils;

import java.util.*;
import java.util.stream.Collectors;

import static net.somta.juggle.core.enums.CoreErrorEnum.FLOW_ELEMENT_IS_EMPTY_ERROR;
import static net.somta.juggle.core.enums.CoreErrorEnum.FLOW_NOT_EXIST_START_NODE_ERROR;

/**
 * @author husong
 * @since 1.0.0
 */
public abstract class AbstractDispatcher implements IDispatcher {

    /**
     * 执行work
     */
    protected IWorkRunner workRunner;

    public AbstractDispatcher(IWorkRunner workRunner) {
        this.workRunner = workRunner;
    }

    @Override
    public FlowResult doDispatcher(Flow flow, Map<String,Object> flowData, IFlowResultManager flowResultManager) {
        //1.校验流程正确性

        //2.构建流程运行的RuntimeContext
        FlowRuntimeContext flowRuntimeContext = buildRuntimeContext(flow);
        flowRuntimeContext.setFlowResultManager(flowResultManager);

        try {
            //3.将提交的入参的数据和入参的变量对应上，并将值设置到变量上，入参值后面要用
            fillInputParameterVariable(flowRuntimeContext.getVariableManager(),flowData);
        } catch (FlowException e) {
            e.printStackTrace();
        }
        return doSend(flowRuntimeContext);
    }

    /**
     * 填充入参变量的值
     * @param variableManager
     * @param flowData
     */
    private void fillInputParameterVariable(AbstractVariableManager variableManager, Map<String,Object> flowData) throws FlowException {
        if(MapUtils.isNotEmpty(flowData)){
            for (String key : flowData.keySet()) {
                variableManager.setVariableValue("env_"+key,flowData.get(key));
            }
        }
    }

    /**
     * 构建流程运行的RuntimeContext
     * @return
     */
    private FlowRuntimeContext buildRuntimeContext(Flow flow){

        Map<String,Variable> variableSchemaMap = flow.getVariables().stream().collect(Collectors.toMap(Variable::getKey, account -> account));

        FlowRuntimeContext flowRuntimeContext = new FlowRuntimeContext(variableSchemaMap);
        flowRuntimeContext.setFlowInstanceId(flow.getFlowInstanceId());
        flowRuntimeContext.setFlowStatus(FlowStatusEnum.INIT);
        flowRuntimeContext.setFlowKey(flow.getFlowKey());
        flowRuntimeContext.setOutputParameters(flow.getOutputParams());
        Map<String, FlowElement> flowElementMap = buildFlowElementMap(flow.getFlowContent());
        if(MapUtils.isEmpty(flowElementMap)){
            throw new FlowException(FLOW_ELEMENT_IS_EMPTY_ERROR);
        }
        flowRuntimeContext.setFlowElementMap(flowElementMap);
        flowRuntimeContext.setCurrentNode(getFirstElement(flowElementMap));
        return flowRuntimeContext;
    }

    /**
     *
     * 构建流程元素Map
     * @param content
     * @return
     */
    private Map<String, FlowElement> buildFlowElementMap(String content){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<FlowElement> elementList = objectMapper.readValue(content, new TypeReference<List<FlowElement>>() {});
            Map<String, FlowElement> flowElementMap = elementList.stream().collect(Collectors.toMap(FlowElement::getKey,e -> e));
            return flowElementMap;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_MAP;
    }

    /**
     * 获取开始节点
     * @return
     */
    private FlowElement getFirstElement(Map<String, FlowElement> flowElementMap){
        for (FlowElement flowElement : flowElementMap.values()) {
            if(flowElement.getElementType() == ElementTypeEnum.START){
                return flowElement;
            }
        }
        throw new FlowException(FLOW_NOT_EXIST_START_NODE_ERROR);
    }

    protected abstract FlowResult doSend(FlowRuntimeContext flowRuntimeContext);
}
