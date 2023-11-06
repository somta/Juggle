package net.somta.juggle.core.result;

import java.util.Map;

/**
 * @author husong
 */
public interface IFlowResultManager {

     boolean putFlowResult(String flowInstanceId,Map<String,Object> resultData);

     Map<String,Object> getFlowResult(String flowInstanceId);

}
