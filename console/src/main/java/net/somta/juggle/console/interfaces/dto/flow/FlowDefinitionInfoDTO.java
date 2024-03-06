package net.somta.juggle.console.interfaces.dto.flow;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;

import java.util.Date;
import java.util.List;

/**
 * @author husong
 */
public class FlowDefinitionInfoDTO {

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    private List<InputParameterVO> flowInputParams;

    private List<OutputParameterVO> flowOutputParams;

    private List<VariableInfoVO> flowVariables;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<InputParameterVO> getFlowInputParams() {
        return flowInputParams;
    }

    public void setFlowInputParams(List<InputParameterVO> flowInputParams) {
        this.flowInputParams = flowInputParams;
    }

    public List<OutputParameterVO> getFlowOutputParams() {
        return flowOutputParams;
    }

    public void setFlowOutputParams(List<OutputParameterVO> flowOutputParams) {
        this.flowOutputParams = flowOutputParams;
    }

    public List<VariableInfoVO> getFlowVariables() {
        return flowVariables;
    }

    public void setFlowVariables(List<VariableInfoVO> flowVariables) {
        this.flowVariables = flowVariables;
    }

    @Override
    public String toString() {
        return "FlowDefinitionInfoDTO{" +
                "id=" + id +
                ", flowKey='" + flowKey + '\'' +
                ", flowName='" + flowName + '\'' +
                ", flowType='" + flowType + '\'' +
                ", flowContent='" + flowContent + '\'' +
                ", remark='" + remark + '\'' +
                ", flowInputParams=" + flowInputParams +
                ", flowOutputParams=" + flowOutputParams +
                '}';
    }
}
