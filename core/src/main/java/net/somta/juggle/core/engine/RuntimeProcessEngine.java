package net.somta.juggle.core.engine;

import net.somta.juggle.core.RuntimeContext;

/**
 * 运行时的执行引擎实现
 * @author husong
 * @date 2022/12/16
 **/
public class RuntimeProcessEngine implements IProcessEngine{
    @Override
    public void startProcess() {

        RuntimeContext runtimeContext = null;
        try {
            //1.param validate
            //ParamValidator.validate(startProcessParam);

            //2.getFlowInfo
            //FlowInfo flowInfo = getFlowInfo(startProcessParam);

            //3.init context for runtime
            //runtimeContext = buildStartProcessContext(flowInfo, startProcessParam.getVariables());

            //4.process
            //flowExecutor.execute(runtimeContext);

            //5.build result
            //return buildStartProcessResult(runtimeContext);
        } catch (Exception e) {
           /* if (!ErrorEnum.isSuccess(e.getErrNo())) {
                LOGGER.warn("startProcess ProcessException.||startProcessParam={}||runtimeContext={}, ",
                        startProcessParam, runtimeContext, e);
            }
            return buildStartProcessResult(runtimeContext, e);*/
        }

    }
}
