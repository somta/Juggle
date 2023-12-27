package net.somta.juggle.core.dispatcher;

import net.somta.juggle.core.exception.FlowException;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowResult;
import net.somta.juggle.core.result.IFlowResultManager;

import java.util.Map;

/**
 * 调度器接口
 * @author husong
 */
public interface IDispatcher {

    /**
     * 分发流程
     * @param flow
     * @param flowData
     * @return
     */
    FlowResult doDispatcher(Flow flow, Map<String,Object> flowData, IFlowResultManager flowResultManager);

}
