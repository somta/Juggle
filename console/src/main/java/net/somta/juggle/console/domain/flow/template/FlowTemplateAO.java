package net.somta.juggle.console.domain.flow.template;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author husong
 */
public class FlowTemplateAO {

    private String templateName;

    private String templateRemark;

    private String templateContent;

    private String flowType;

    private String inputs;

    private String outputs;

    private String variables;

    /**
     * Initialize parameter entities
     */
    public ParameterEntity initParameterEntity() {
        ParameterEntity parameterEntity = new ParameterEntity();
        if(StringUtils.isNotEmpty(this.inputs)){
            List<InputParameterVO> flowInputParamList = null;
            try {
                flowInputParamList = JsonSerializeHelper.deserialize(this.inputs, List.class, InputParameterVO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            parameterEntity.setInputParameterList(flowInputParamList);
        }
        if(StringUtils.isNotEmpty(this.inputs)){
            List<OutputParameterVO> flowOutputParamList = null;
            try {
                flowOutputParamList = JsonSerializeHelper.deserialize(this.outputs, List.class, OutputParameterVO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            parameterEntity.setOutputParameterList(flowOutputParamList);
        }
        return parameterEntity;
    }

    /**
     * 获取流程信息列表
     * @return
     */
    public List<VariableInfoVO> getFlowVariableInfoList(){
        List<VariableInfoVO> variableInfoList = null;
        if(StringUtils.isNotEmpty(this.variables)){
            try {
                variableInfoList = JsonSerializeHelper.deserialize(this.variables,List.class,VariableInfoVO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return variableInfoList;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateRemark() {
        return templateRemark;
    }

    public void setTemplateRemark(String templateRemark) {
        this.templateRemark = templateRemark;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public String getOutputs() {
        return outputs;
    }

    public void setOutputs(String outputs) {
        this.outputs = outputs;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }
}
