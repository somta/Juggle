package net.somta.juggle.core.result;

import java.util.Map;

public interface IFlowResultManager {

     boolean putFlowResult(String flowInstanceId,Map<String,Object> resultData);

     Map<String,Object> getFlowResult(String flowInstanceId);

}
