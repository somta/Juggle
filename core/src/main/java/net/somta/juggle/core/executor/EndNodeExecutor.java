package net.somta.juggle.core.executor;

import net.somta.juggle.core.FlowRuntimeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 开始节点的执行器
 *
 * @author husong
 */
public class EndNodeExecutor extends AbstractElementExecutor {
    private final static Logger logger = LoggerFactory.getLogger(EndNodeExecutor.class);

    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {
        logger.debug("结束节点执行器，执行前。。。");
    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        logger.debug("结束节点执行器，执行中。。。");
    }

    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        logger.debug("结束节点执行器，执行后========================================");
    }
}
