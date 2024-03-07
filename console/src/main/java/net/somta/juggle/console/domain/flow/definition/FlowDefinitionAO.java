package net.somta.juggle.console.domain.flow.definition;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.expression.ExpressionManager;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.model.node.ConditionNode.*;
import net.somta.juggle.core.model.node.EndNode;
import net.somta.juggle.core.model.node.StartNode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void initDefaultFlowContent(String flowName) {
        List<FlowElement> elementList = new ArrayList<>();

        String startNodeKey = "start_" + RandomStringUtils.random(8, true, true);
        String endNodeKey = "end_" + RandomStringUtils.random(8, true, true);
        StartNode startEventNode = new StartNode();
        startEventNode.setKey(startNodeKey);
        startEventNode.setName(flowName);
        startEventNode.setElementType(ElementTypeEnum.START);
        startEventNode.setOutgoings(Arrays.asList(endNodeKey));
        elementList.add(startEventNode);

        EndNode endEventNode = new EndNode();
        endEventNode.setKey(endNodeKey);
        endEventNode.setName("结束");
        endEventNode.setElementType(ElementTypeEnum.END);
        endEventNode.setIncomings(Arrays.asList(startNodeKey));
        elementList.add(endEventNode);
        String content = JsonSerializeHelper.serialize(elementList);
        this.flowContent = content;
    }

    public void initParameterList(List<InputParameterVO> flowInputParamList, List<OutputParameterVO> flowOutputParamList) {
        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.setInputParameterList(flowInputParamList);
        parameterEntity.setOutputParameterList(flowOutputParamList);
        this.parameterEntity = parameterEntity;
    }

    /**
     * 处理流程内容
     */
    public void processFlowContent(){
        List<FlowElement> elementList = JsonSerializeHelper.deserialize(this.flowContent,List.class,FlowElement.class);
        elementList.stream()
                .filter(flowElement -> ElementTypeEnum.CONDITION.equals(flowElement.getElementType()))
                .forEach(node ->{
                    ConditionNode conditionNode = (ConditionNode) node;
                    List<ConditionItem> conditionItems = conditionNode.getConditions();
                    for (ConditionItem conditionItem : conditionItems) {
                        if(CollectionUtils.isNotEmpty(conditionItem.getConditionExpressions())){
                            conditionItem.setExpression(ExpressionManager.generateExpression(conditionItem.getConditionExpressions()));
                        }
                    }
                });
        this.flowContent = JsonSerializeHelper.serialize(elementList);
    }

    /**
     * 获取运行时的变量列表
     * @return
     */
    public List<Variable> getFlowRuntimeVariables(){
        List<Variable> variables = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(this.variableInfoList)){
            Variable variable = null;
            for (VariableInfoVO variableInfoVo : variableInfoList) {
                variable = new Variable();
                variable.setKey(variableInfoVo.getEnvKey());
                variable.setName(variableInfoVo.getEnvName());
                variable.setDataType(JsonSerializeHelper.deserialize(variableInfoVo.getDataType(), DataType.class));
                variables.add(variable);
            }
        }
        return variables;
    }

    /**
     * Generate flow key
     * @return Flow key string
     */
    public String generateFlowKey(){
        String flowKey = this.flowType + "_" + RandomStringUtils.random(10, true, true);
        return flowKey;
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
