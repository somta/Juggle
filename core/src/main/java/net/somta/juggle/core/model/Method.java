package net.somta.juggle.core.model;

import net.somta.juggle.core.enums.RequestContentTypeEnum;
import net.somta.juggle.core.enums.RequestTypeEnum;
import net.somta.juggle.core.model.node.MethodNode;

import java.util.List;

/**
 * 方法类，承载一个Http的请求信息
 */
public class Method {

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求类型 GET POST
     */
    private RequestTypeEnum requestType;

    /**
     * 请求内容类型 application/json; charset=UTF-8
     */
    private RequestContentTypeEnum requestContentType;

    /**
     * 入参列表
     */
    //private List<InputParameter> inputParameters;

    /**
     * 出参列表
     */
    //private List<OutputParameter> outputParameters;

    /**
     * 入参的填充规则
     */
    private List<FillStruct> inputFillRules;

    /**
     * 出参的填充规则
     */
    private List<FillStruct> outputFillRules;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestTypeEnum getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestTypeEnum requestType) {
        this.requestType = requestType;
    }

    public RequestContentTypeEnum getRequestContentType() {
        return requestContentType;
    }

    public void setRequestContentType(RequestContentTypeEnum requestContentType) {
        this.requestContentType = requestContentType;
    }

    /*public List<InputParameter> getInputParameters() {
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
    }*/

    public List<FillStruct> getInputFillRules() {
        return inputFillRules;
    }

    public void setInputFillRules(List<FillStruct> inputFillRules) {
        this.inputFillRules = inputFillRules;
    }

    public List<FillStruct> getOutputFillRules() {
        return outputFillRules;
    }

    public void setOutputFillRules(List<FillStruct> outputFillRules) {
        this.outputFillRules = outputFillRules;
    }

}
