package net.somta.juggle.console.interfaces.param.flow.definition;

import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;

import java.util.List;

/**
 * @author Gavin
 */
public class FlowDefinitionUpdateParam {
    private Long id;

    private String flowName;

    private String flowType;

    private String remark;

    private List<InputParameterVO> flowInputParams;

    private List<OutputParameterVO> flowOutputParams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
