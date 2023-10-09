package net.somta.juggle.console.domain.definition;

import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.variable.VariableInfoEntity;

import java.util.List;

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

    private String flowContent;
    /**
     * 流程描述
     */
    private String remark;

    private ParameterEntity parameterEntity;

    public void initParameterList(List<InputParameterVO> flowInputParamList, List<OutputParameterVO> flowOutputParamList) {
        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.setInputParameterList(flowInputParamList);
        parameterEntity.setOutputParameterList(flowOutputParamList);
        this.parameterEntity = parameterEntity;
    }

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

    public String getFlowContent() {
        return flowContent;
    }

    public void setFlowContent(String flowContent) {
        this.flowContent = flowContent;
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



}
