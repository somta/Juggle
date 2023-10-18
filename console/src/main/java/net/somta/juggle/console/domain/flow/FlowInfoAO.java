package net.somta.juggle.console.domain.flow;

import net.somta.juggle.console.domain.version.enums.FlowVersionStatusEnum;
import net.somta.juggle.console.domain.version.vo.FlowVersionVO;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class FlowInfoAO {

    private String flowVersion;

    private String flowKey;

    private String flowName;

    private String flowType;

    private String flowContent;

    private String inputs;

    private String outputs;

    private String variables;

    private String remark;

    private List<FlowVersionVO> flowVersionList;

    public void initFlowVersionList(List<FlowVersionVO> flowVersionList){
        this.flowVersionList = flowVersionList;
    }

    public Boolean isExistEnableVersion(){
        if(CollectionUtils.isEmpty(this.flowVersionList)){
            return false;
        }
        List<FlowVersionVO> enableFlowVersionList = this.flowVersionList.stream().filter(v -> FlowVersionStatusEnum.ENABLE.getCode().equals(v.getFlowStatus()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(enableFlowVersionList)){
            return false;
        }
        return true;
    }

    public String getFlowVersion() {
        return flowVersion;
    }

    public void setFlowVersion(String flowVersion) {
        this.flowVersion = flowVersion;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<FlowVersionVO> getFlowVersionList() {
        return flowVersionList;
    }
}
