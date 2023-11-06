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

    /**
     * 将参数的po对象转成对应的出入参VO对象
     * @param parameterPoList
     */
    public void parseParameter(List<ParameterPO> parameterPoList) {
        List<ParameterPO> inputParameterPoList = parameterPoList.stream()
                .filter(parameter -> ParameterTypeEnum.INPUT_PARAM.getCode() == parameter.getParamType())
                .collect(Collectors.toList());
        this.inputParameterList = IParameterConverter.IMPL.inputParamerterPoListToVoList(inputParameterPoList);
        List<ParameterPO> outputParameterPoList = parameterPoList.stream()
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
                ParameterPO inputParameterPo = new ParameterPO();
                inputParameterPo.setParamKey(inputParameterVO.getParamKey());
                inputParameterPo.setParamType(ParameterTypeEnum.INPUT_PARAM.getCode());
                inputParameterPo.setParamName(inputParameterVO.getParamName());
                inputParameterPo.setDataType(inputParameterVO.getDataType());
                inputParameterPo.setRequired(inputParameterVO.getRequired());
                inputParameterPo.setSourceId(sourceId);
                inputParameterPo.setSourceType(sourceType);
                inputParameterPo.setCreatedAt(currentDate);
                parameterPoList.add(inputParameterPo);
            }
        }
        if(CollectionUtils.isNotEmpty(this.outputParameterList)){
            for (OutputParameterVO outputParameterVO : this.outputParameterList) {
                ParameterPO outputParameterPo = new ParameterPO();
                outputParameterPo.setParamKey(outputParameterVO.getParamKey());
                outputParameterPo.setParamType(ParameterTypeEnum.OUTPUT_PARAM.getCode());
                outputParameterPo.setParamName(outputParameterVO.getParamName());
                outputParameterPo.setDataType(outputParameterVO.getDataType());
                outputParameterPo.setSourceId(sourceId);
                outputParameterPo.setSourceType(sourceType);
                outputParameterPo.setCreatedAt(currentDate);
                parameterPoList.add(outputParameterPo);
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
