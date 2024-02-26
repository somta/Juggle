package net.somta.juggle.console.domain.flow.definition.repository;

import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;

import java.util.List;

/**
 * @author husong
 */
public interface IVariableInfoRepository {

    List<VariableInfoVO> queryVariableInfoList(Long flowDefinitionId);
}
