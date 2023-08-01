package net.somta.juggle.console.interfaces.param;

import java.util.List;

/**
 * @author Gavin
 */
public class FlowDefinitionParam {

    private Long id;

    private String flowName;

    private String flowType;

    private String remark;

    private List<InputParameterParam> flowInputParams;

    private List<OutputParameterParam> flowOutputParams;

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

    public List<InputParameterParam> getFlowInputParams() {
        return flowInputParams;
    }

    public void setFlowInputParams(List<InputParameterParam> flowInputParams) {
        this.flowInputParams = flowInputParams;
    }

    public List<OutputParameterParam> getFlowOutputParams() {
        return flowOutputParams;
    }

    public void setFlowOutputParams(List<OutputParameterParam> flowOutputParams) {
        this.flowOutputParams = flowOutputParams;
    }
}
