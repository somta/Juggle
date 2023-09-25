package net.somta.juggle.console.domain.definition;

import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.variable.VariableInfoEntity;

/**
 * @author husong
 */
public class FlowDefinitionAO {

    private Long id;
    /**
     * 流程Key,全局唯一
     */
    private String flowKey;
    /**
     * 流程名称
     */
    private String flowName;
    /**
     * 流程类型  sync：同步  async：异步
     */
    private String flowType;
    /**
     * 流程描述
     */
    private String remark;

    private ParameterEntity parameterEntity;

    private VariableInfoEntity variableInfoEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ParameterEntity getParameterEntity() {
        return parameterEntity;
    }

    public void setParameterEntity(ParameterEntity parameterEntity) {
        this.parameterEntity = parameterEntity;
    }

    public VariableInfoEntity getVariableInfoEntity() {
        return variableInfoEntity;
    }

    public void setVariableInfoEntity(VariableInfoEntity variableInfoEntity) {
        this.variableInfoEntity = variableInfoEntity;
    }
}
