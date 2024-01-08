package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.application.assembler.IVariableInfoAssembler;
import net.somta.juggle.console.application.service.IVariableInfoService;
import net.somta.juggle.console.domain.variable.VariableInfoEntity;
import net.somta.juggle.console.domain.variable.enums.VariableTypeEnum;
import net.somta.juggle.console.domain.variable.repository.IVariableInfoRepository;
import net.somta.juggle.console.domain.variable.vo.VariableInfoVO;
import net.somta.juggle.console.interfaces.dto.VariableInfoDTO;
import net.somta.juggle.console.interfaces.param.VariableAddParam;
import net.somta.juggle.console.interfaces.param.VariableUpdateParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 */
@Service
public class VariableInfoServiceImpl implements IVariableInfoService {

    private final IVariableInfoRepository variableInfoRepository;

    public VariableInfoServiceImpl(IVariableInfoRepository variableInfoRepository) {
        this.variableInfoRepository = variableInfoRepository;
    }

    @Override
    public Boolean addVariable(VariableAddParam variableAddParam) {
        VariableInfoEntity variableInfoEntity = IVariableInfoAssembler.IMPL.paramToEntity(variableAddParam);
        variableInfoEntity.setEnvType(VariableTypeEnum.MIDDLE_VARIABLE);
        return variableInfoRepository.addVariable(variableInfoEntity);
    }

    @Override
    public Boolean deleteVariable(Long flowDefinitionId, Long variableId) {
        return variableInfoRepository.deleteVariableById(variableId);
    }

    @Override
    public Boolean updateVariable(VariableUpdateParam variableUpdateParam) {
        VariableInfoEntity variableInfoEntity = IVariableInfoAssembler.IMPL.paramToEntity(variableUpdateParam);
        variableInfoEntity.setEnvType(VariableTypeEnum.MIDDLE_VARIABLE);
        return variableInfoRepository.updateVariable(variableUpdateParam.getId(), variableInfoEntity);
    }

    @Override
    public List<VariableInfoDTO> getVariableInfoList(Long flowDefinitionId) {
        List<VariableInfoVO> variableInfoList = variableInfoRepository.queryVariableInfoList(flowDefinitionId);
        List<VariableInfoDTO> variableInfoDTOList = IVariableInfoAssembler.IMPL.voListToDtoList(variableInfoList);
        return variableInfoDTOList;
    }

}
