package net.somta.juggle.core.model.node;

import net.somta.juggle.core.model.FillStruct;
import net.somta.juggle.core.model.InputParameter;
import net.somta.juggle.core.model.OutputParameter;

import java.util.List;

/**
 * 方法节点，主要承载接口请求
 *
 * @author husong
 * @date 2023/01/17
 */
public class MethodNode extends FlowNode {
    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求类型 GET POST
     */
    private String requestType;

    /**
     * 入参列表
     */
    private List<InputParameter> inputParameters;

    /**
     * 出参列表
     */
    private List<OutputParameter> outputParameters;

    /**
     * 入参的填充规则
     */
    private List<FillStruct> inputFillRules;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public List<InputParameter> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(List<InputParameter> inputParameters) {
        this.inputParameters = inputParameters;
    }

    public List<OutputParameter> getOutputParameters() {
        return outputParameters;
    }

    public void setOutputParameters(List<OutputParameter> outputParameters) {
        this.outputParameters = outputParameters;
    }

    public List<FillStruct> getInputFillRules() {
        return inputFillRules;
    }

    public void setInputFillRules(List<FillStruct> inputFillRules) {
        this.inputFillRules = inputFillRules;
    }
}
