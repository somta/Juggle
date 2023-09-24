package net.somta.juggle.console.domain.variable.repository;

import net.somta.juggle.console.domain.variable.VariableInfoEntity;


public interface IVariableInfoRepository {
    VariableInfoEntity queryVariableInfo(Long flowDefinitionId);
}
