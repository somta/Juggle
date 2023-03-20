package net.somta.juggle.console.service;

import net.somta.juggle.core.model.Variable;

import java.util.List;

public interface IVariableInfoService {



    void addVariable();

    List<Variable> getFlowVariableList(Integer flowDefinitionId);
}
