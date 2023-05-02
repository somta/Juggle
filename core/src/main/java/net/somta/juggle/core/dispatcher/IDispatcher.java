package net.somta.juggle.core.dispatcher;

import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.model.FlowDefinition;

import java.util.List;
import java.util.Map;

/**
 * 调度器接口
 */
public interface IDispatcher {

    /**
     * 分发流程
     * @param flow
     * @param flowData
     * @return
     */
    Boolean doDispatcher(Flow flow, Map<String,Object> flowData);

}
