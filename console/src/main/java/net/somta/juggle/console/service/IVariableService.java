package net.somta.juggle.console.service;

import net.somta.juggle.core.model.Variable;

import java.util.List;

public interface IVariableService {



    void addVariable();

    List<Variable> getFlowVariableList(Integer flowDefinitionId);
}
