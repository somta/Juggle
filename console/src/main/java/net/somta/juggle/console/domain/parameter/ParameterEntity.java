package net.somta.juggle.console.domain.parameter;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.InputParameter;
import net.somta.juggle.core.model.OutputParameter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author husong
 */
public class ParameterEntity {

    private List<ParameterPO> inputParameterPOList;

    private List<ParameterPO> outputParameterPOList;

    public List<InputParameterVO> getInputParameter(){
        List<InputParameterVO> inputParameterVOList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(this.inputParameterPOList)){
            for (ParameterPO parameterPO : this.inputParameterPOList) {
                InputParameterVO inputParameterVO = new InputParameterVO();
                inputParameterVO.setParamKey(parameterPO.getParamKey());
                inputParameterVO.setParamName(parameterPO.getParamName());
                inputParameterVO.setDataType(parameterPO.getDataType());
                inputParameterVO.setRequired(parameterPO.getRequired());
                inputParameterVOList.add(inputParameterVO);
            }
        }
        return inputParameterVOList;
    }

    /**
     *
     * @param inputParameterVOS
     * @param sourceId
     * @param sourceType
     * @return
     */
    public ParameterEntity setInputParameter(List<InputParameterVO> inputParameterVOS,Long sourceId, String sourceType){
        this.inputParameterPOList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(inputParameterVOS)){
            for (InputParameterVO inputParameterVO : inputParameterVOS) {
                ParameterPO parameterPO = new ParameterPO();
                parameterPO.setParamKey(inputParameterVO.getParamKey());
                parameterPO.setParamType(ParameterTypeEnum.INPUT_PARAM.getCode());
                parameterPO.setParamName(inputParameterVO.getParamName());
                parameterPO.setDataType(inputParameterVO.getDataType());
                parameterPO.setRequired(inputParameterVO.getRequired());
                parameterPO.setSourceType(sourceType);
                parameterPO.setSourceId(sourceId);
                parameterPO.setCreatedAt(new Date());
                inputParameterPOList.add(parameterPO);
            }
        }
        return this;
    }

    public List<OutputParameterVO> getOutputParameter(){
        List<OutputParameterVO> outputParameterVOParameterVOList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(this.outputParameterPOList)){
            for (ParameterPO parameterPO : this.outputParameterPOList) {
                OutputParameterVO outputParameterVO = new OutputParameterVO();
                outputParameterVO.setParamKey(parameterPO.getParamKey());
                outputParameterVO.setParamName(parameterPO.getParamName());
                outputParameterVO.setDataType(parameterPO.getDataType());
                outputParameterVOParameterVOList.add(outputParameterVO);
            }
        }
        return outputParameterVOParameterVOList;
    }

    /**
     *
     * @param outputParameterVOS
     * @param sourceId
     * @param sourceType
     * @return
     */
    public ParameterEntity setOutputParameter(List<OutputParameterVO> outputParameterVOS, Long sourceId, String sourceType){
        this.outputParameterPOList = new ArrayList<>();
        for (OutputParameterVO outputParameterVO : outputParameterVOS) {
            ParameterPO parameterPO = new ParameterPO();
            parameterPO.setParamKey(outputParameterVO.getParamKey());
            parameterPO.setParamType(ParameterTypeEnum.OUTPUT_PARAM.getCode());
            parameterPO.setParamName(outputParameterVO.getParamName());
            parameterPO.setDataType(outputParameterVO.getDataType());
            parameterPO.setSourceType(sourceType);
            parameterPO.setSourceId(sourceId);
            parameterPO.setCreatedAt(new Date());
            outputParameterPOList.add(parameterPO);
        }
        return this;
    }

    /**
     *
     * @param parameterPOS
     */
    public void parseParameter(List<ParameterPO> parameterPOS) {
        this.inputParameterPOList = parameterPOS.stream()
                .filter(parameter -> ParameterTypeEnum.INPUT_PARAM.getCode() == parameter.getParamType())
                .collect(Collectors.toList());
        this.outputParameterPOList = parameterPOS.stream()
                .filter(parameter -> ParameterTypeEnum.OUTPUT_PARAM.getCode() == parameter.getParamType())
                .collect(Collectors.toList());
    }

    /**
     * 获取流程运行时入参
     * @return
     */
    public List<InputParameter> getFlowRuntimeInputParameters(){
        List<InputParameter> inputParams = new ArrayList<>();
        if(CollectionUtils.isEmpty(this.inputParameterPOList)){
            return inputParams;
        }
        for (ParameterPO parameterPO : inputParameterPOList) {
            InputParameter inputParameter = new InputParameter();
            inputParameter.setKey(parameterPO.getParamKey());
            inputParameter.setName(parameterPO.getParamName());
            inputParameter.setDataType(JsonSerializeHelper.deserialize(parameterPO.getDataType(), DataType.class));
            inputParameter.setRequired(parameterPO.getRequired());
            inputParams.add(inputParameter);
        }
        return inputParams;
    }

    /**
     * 获取流程运行时出参
     * @return
     */
    public List<OutputParameter> getFlowRuntimeOutputParameters(){
        List<OutputParameter> outputParams = new ArrayList<>();
        if(CollectionUtils.isEmpty(this.outputParameterPOList)){
            return outputParams;
        }
        for (ParameterPO parameterPO : outputParameterPOList) {
            OutputParameter outputParameter = new OutputParameter();
            outputParameter.setKey(parameterPO.getParamKey());
            outputParameter.setName(parameterPO.getParamName());
            outputParameter.setDataType(JsonSerializeHelper.deserialize(parameterPO.getDataType(),DataType.class));
            outputParams.add(outputParameter);
        }
        return outputParams;
    }

    public List<ParameterPO> getInputParameterList() {
        return inputParameterPOList;
    }

    public List<ParameterPO> getOutputParameterList() {
        return outputParameterPOList;
    }

    public void setInputParameterList(List<ParameterPO> inputParameterPOList) {
        this.inputParameterPOList = inputParameterPOList;
    }

    public void setOutputParameterList(List<ParameterPO> outputParameterPOList) {
        this.outputParameterPOList = outputParameterPOList;
    }
}
