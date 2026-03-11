package com.matecoder.juggle.console.domain.flow.definition.repository;

import com.matecoder.juggle.console.domain.flow.definition.vo.VariableInfoVO;

import java.util.List;

/**
 * @author husong
 */
public interface IVariableInfoRepository {

    List<VariableInfoVO> queryVariableInfoList(Long flowDefinitionId);
}
