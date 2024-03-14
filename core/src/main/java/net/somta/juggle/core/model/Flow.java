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

package net.somta.juggle.core.model;

import java.util.List;

/**
 * 流程类
 * @author husong
 * @since 1.0.0
 **/
public class Flow {

    private String flowInstanceId;

    /**
     * 流程Key
     */
    private String flowKey;

    /**
     * 流程名称
     */
    private String flowName;

    /**
     * 流程内容
     */
    private String flowContent;
    /**
     * 入参列表
     */
    private List<InputParameter> inputParams;
    /**
     * 出参列表
     */
    private List<OutputParameter> outputParams;
    /**
     * 变量列表
     */
    private List<Variable> variables;

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

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowContent() {
        return flowContent;
    }

    public void setFlowContent(String flowContent) {
        this.flowContent = flowContent;
    }

    public List<InputParameter> getInputParams() {
        return inputParams;
    }

    public void setInputParams(List<InputParameter> inputParams) {
        this.inputParams = inputParams;
    }

    public List<OutputParameter> getOutputParams() {
        return outputParams;
    }

    public void setOutputParams(List<OutputParameter> outputParams) {
        this.outputParams = outputParams;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
}
