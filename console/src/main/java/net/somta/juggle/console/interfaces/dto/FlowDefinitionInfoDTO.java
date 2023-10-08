package net.somta.juggle.console.interfaces.dto;

import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;

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

    private List<InputParameterVO> flowInputParams;

    private List<OutputParameterVO> flowOutputParams;


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
}
