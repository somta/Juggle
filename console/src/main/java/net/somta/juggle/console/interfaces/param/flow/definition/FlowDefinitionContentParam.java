package net.somta.juggle.console.interfaces.param.flow.definition;

import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;

import java.util.List;

/**
 * @author husong
 */
public class FlowDefinitionContentParam {
    private Long id;
    private String flowContent;
    private List<VariableInfoVO> flowVariables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowContent() {
        return flowContent;
    }

    public void setFlowContent(String flowContent) {
        this.flowContent = flowContent;
    }

    public List<VariableInfoVO> getFlowVariables() {
        return flowVariables;
    }

    public void setFlowVariables(List<VariableInfoVO> flowVariables) {
        this.flowVariables = flowVariables;
    }
}
