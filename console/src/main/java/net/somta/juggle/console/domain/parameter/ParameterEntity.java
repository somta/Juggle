package net.somta.juggle.console.domain.parameter;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.infrastructure.converter.IParameterConverter;
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

    private List<InputParameterVO> inputParameterList;

    private List<OutputParameterVO> outputParameterList;

    /*public List<InputParameterVO> getInputParameter(){
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
    }*/

    /**
     *
     * @param inputParameterVOS
     * @param sourceId
     * @param sourceType
     * @return
     */
    /*public ParameterEntity setInputParameter(List<InputParameterVO> inputParameterVOS,Long sourceId, String sourceType){
        this.inputParameterList = new ArrayList<>();
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
                inputParameterList.add(parameterPO);
            }
        }
        return this;
    }*/

    /*public List<OutputParameterVO> getOutputParameter(){
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
    }*/

    /**
     *
     * @param outputParameterVOS
     * @param sourceId
     * @param sourceType
     * @return
     */
  /*  public ParameterEntity setOutputParameter(List<OutputParameterVO> outputParameterVOS, Long sourceId, String sourceType){
        this.outputParameterList = new ArrayList<>();
        for (OutputParameterVO outputParameterVO : outputParameterVOS) {
            ParameterPO parameterPO = new ParameterPO();
            parameterPO.setParamKey(outputParameterVO.getParamKey());
            parameterPO.setParamType(ParameterTypeEnum.OUTPUT_PARAM.getCode());
            parameterPO.setParamName(outputParameterVO.getParamName());
            parameterPO.setDataType(outputParameterVO.getDataType());
            parameterPO.setSourceType(sourceType);
            parameterPO.setSourceId(sourceId);
            parameterPO.setCreatedAt(new Date());
            outputParameterList.add(parameterPO);
        }
        return this;
    }*/

    /**
     * 将参数的po对象转成对应的出入参VO对象
     * @param parameterPOS
     */
    public void parseParameter(List<ParameterPO> parameterPOS) {
        List<ParameterPO> inputParameterPoList = parameterPOS.stream()
                .filter(parameter -> ParameterTypeEnum.INPUT_PARAM.getCode() == parameter.getParamType())
                .collect(Collectors.toList());
        this.inputParameterList = IParameterConverter.IMPL.inputParamerterPoListToVoList(inputParameterPoList);
        List<ParameterPO> outputParameterPoList = parameterPOS.stream()
                .filter(parameter -> ParameterTypeEnum.OUTPUT_PARAM.getCode() == parameter.getParamType())
                .collect(Collectors.toList());
        this.outputParameterList = IParameterConverter.IMPL.outputParamerterPoListToVoList(outputParameterPoList);
    }

    /**
     * 根据出入参获取参数PO
     * @return
     */
    public List<ParameterPO> getParameterPoList(Long sourceId,String sourceType){
        List<ParameterPO> parameterPoList = new ArrayList<>();
        Date currentDate = new Date();
        if(CollectionUtils.isNotEmpty(this.inputParameterList)){
            for (InputParameterVO inputParameterVO : this.inputParameterList) {
                ParameterPO inputParameterPO = new ParameterPO();
                inputParameterPO.setParamKey(inputParameterVO.getParamKey());
                inputParameterPO.setParamType(ParameterTypeEnum.INPUT_PARAM.getCode());
                inputParameterPO.setParamName(inputParameterVO.getParamName());
                inputParameterPO.setDataType(inputParameterVO.getDataType());
                inputParameterPO.setRequired(inputParameterVO.getRequired());
                inputParameterPO.setSourceId(sourceId);
                inputParameterPO.setSourceType(sourceType);
                inputParameterPO.setCreatedAt(currentDate);
                parameterPoList.add(inputParameterPO);
            }
        }
        if(CollectionUtils.isNotEmpty(this.outputParameterList)){
            for (OutputParameterVO outputParameterVO : this.outputParameterList) {
                ParameterPO outputParameterPO = new ParameterPO();
                outputParameterPO.setParamKey(outputParameterVO.getParamKey());
                outputParameterPO.setParamType(ParameterTypeEnum.OUTPUT_PARAM.getCode());
                outputParameterPO.setParamName(outputParameterVO.getParamName());
                outputParameterPO.setDataType(outputParameterVO.getDataType());
                outputParameterPO.setSourceId(sourceId);
                outputParameterPO.setSourceType(sourceType);
                outputParameterPO.setCreatedAt(currentDate);
                parameterPoList.add(outputParameterPO);
            }
        }
        return parameterPoList;
    }

    /**
     * 获取流程运行时入参
     * @return
     */
    public List<InputParameter> getFlowRuntimeInputParameters(){
        List<InputParameter> inputParams = new ArrayList<>();
        if(CollectionUtils.isEmpty(this.inputParameterList)){
            return inputParams;
        }
        for (InputParameterVO inputParameterVO : inputParameterList) {
            InputParameter inputParameter = new InputParameter();
            inputParameter.setKey(inputParameterVO.getParamKey());
            inputParameter.setName(inputParameterVO.getParamName());
            inputParameter.setDataType(JsonSerializeHelper.deserialize(inputParameterVO.getDataType(), DataType.class));
            inputParameter.setRequired(inputParameterVO.getRequired());
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
        if(CollectionUtils.isEmpty(this.outputParameterList)){
            return outputParams;
        }
        for (OutputParameterVO outputParameterVO : outputParameterList) {
            OutputParameter outputParameter = new OutputParameter();
            outputParameter.setKey(outputParameterVO.getParamKey());
            outputParameter.setName(outputParameterVO.getParamName());
            outputParameter.setDataType(JsonSerializeHelper.deserialize(outputParameterVO.getDataType(),DataType.class));
            outputParams.add(outputParameter);
        }
        return outputParams;
    }

    public List<InputParameterVO> getInputParameterList() {
        return inputParameterList;
    }

    public void setInputParameterList(List<InputParameterVO> inputParameterList) {
        this.inputParameterList = inputParameterList;
    }

    public List<OutputParameterVO> getOutputParameterList() {
        return outputParameterList;
    }

    public void setOutputParameterList(List<OutputParameterVO> outputParameterList) {
        this.outputParameterList = outputParameterList;
    }
}
