package net.somta.juggle.console.interfaces.param;

import java.util.Map;

public class ApiDebugParam {
    private Map<String,Object> headerData;
    private Map<String,Object> inputParamData;

    public Map<String, Object> getHeaderData() {
        return headerData;
    }

    public void setHeaderData(Map<String, Object> headerData) {
        this.headerData = headerData;
    }

    public Map<String, Object> getInputParamData() {
        return inputParamData;
    }

    public void setInputParamData(Map<String, Object> inputParamData) {
        this.inputParamData = inputParamData;
    }
}
