package net.somta.juggle.console.domain.definition;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.variable.VariableInfoEntity;
import net.somta.juggle.console.domain.variable.vo.VariableInfoVO;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author husong
 */
public class FlowDefinitionAO {

    private Long id;
    /**
     * flow Key, globally unique
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

    private List<VariableInfoVO> variableInfoList;


    public void initParameterList(List<InputParameterVO> flowInputParamList, List<OutputParameterVO> flowOutputParamList) {
        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.setInputParameterList(flowInputParamList);
        parameterEntity.setOutputParameterList(flowOutputParamList);
        this.parameterEntity = parameterEntity;
    }

    /**
     * 获取运行时的变量列表
     * @return
     */
    public List<Variable> getFlowRuntimeVariables(){
        List<Variable> variables = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(this.variableInfoList)){
            for (VariableInfoVO variableInfoVo : variableInfoList) {
                Variable variable = new Variable();
                variable.setKey(variableInfoVo.getEnvKey());
                variable.setName(variableInfoVo.getEnvName());
                variable.setDataType(JsonSerializeHelper.deserialize(variableInfoVo.getDataType(), DataType.class));
                variables.add(variable);
            }
        }
        return variables;
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

    public List<VariableInfoVO> getVariableInfoList() {
        return variableInfoList;
    }

    public void setVariableInfoList(List<VariableInfoVO> variableInfoList) {
        this.variableInfoList = variableInfoList;
    }
}
