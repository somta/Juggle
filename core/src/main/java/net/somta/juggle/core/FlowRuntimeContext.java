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

package net.somta.juggle.core;

import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.executor.data.IDataSource;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.OutputParameter;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.result.IFlowResultManager;
import net.somta.juggle.core.variable.MemoryVariableManager;
import net.somta.juggle.core.variable.AbstractVariableManager;

import java.util.List;
import java.util.Map;

/**
 * 流程执行的上下文
 * @author husong
 * @since 1.0.0
 **/
public class FlowRuntimeContext {


    private String flowInstanceId;
    /**
     * 流程Key,全局唯一
     */
    private String flowKey;
    /**
     * 流程元素Map
     */
    private Map<String, FlowElement> flowElementMap;

    /**
     * 当前的流程元素
     */
    private FlowElement currentNode;

    /**
     * 下一个流程元素
     */
    private FlowElement nextNode;

    /**
     * 流程出参
     */
    private List<OutputParameter> outputParameters;

    /**
     * 变量管理器
     */
    private AbstractVariableManager variableManager;

    /**
     * 结果管理器
     */
    private IFlowResultManager flowResultManager;

    private IDataSource dataSourceManager;

    /**
     * 流程状态
     */
    private FlowStatusEnum flowStatus;


    public FlowRuntimeContext(Map<String, Variable> variableSchemaMap) {
        // 变量管理器只能根据流程的上下文来，因为不同的可以在不同流程中可能是会一样的
        this.variableManager = new MemoryVariableManager(variableSchemaMap);
    }

    public String getFlowInstanceId() {
        return flowInstanceId;
    }

    public void setFlowInstanceId(String flowInstanceId) {
        this.flowInstanceId = flowInstanceId;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public Map<String, FlowElement> getFlowElementMap() {
        return flowElementMap;
    }

    public void setFlowElementMap(Map<String, FlowElement> flowElementMap) {
        this.flowElementMap = flowElementMap;
    }

    public FlowElement getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(FlowElement currentNode) {
        this.currentNode = currentNode;
    }

    public FlowElement getNextNode() {
        return nextNode;
    }

    public void setNextNode(FlowElement nextNode) {
        this.nextNode = nextNode;
    }

    public List<OutputParameter> getOutputParameters() {
        return outputParameters;
    }

    public void setOutputParameters(List<OutputParameter> outputParameters) {
        this.outputParameters = outputParameters;
    }

    public AbstractVariableManager getVariableManager() {
        return variableManager;
    }

    public IFlowResultManager getFlowResultManager() {
        return flowResultManager;
    }

    public void setFlowResultManager(IFlowResultManager flowResultManager) {
        this.flowResultManager = flowResultManager;
    }

    public IDataSource getDataSourceManager() {
        return dataSourceManager;
    }

    public void setDataSourceManager(IDataSource dataSourceManager) {
        this.dataSourceManager = dataSourceManager;
    }

    public FlowStatusEnum getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(FlowStatusEnum flowStatus) {
        this.flowStatus = flowStatus;
    }

}
