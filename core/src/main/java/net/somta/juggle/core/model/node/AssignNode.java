package net.somta.juggle.core.model.node;

import net.somta.juggle.core.model.FillStruct;

import java.util.List;

public class AssignNode extends FlowNode{

    private List<FillStruct> assignRules;

    public List<FillStruct> getAssignRules() {
        return assignRules;
    }

    public void setAssignRules(List<FillStruct> assignRules) {
        this.assignRules = assignRules;
    }
}
