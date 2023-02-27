package net.somta.juggle.core.model.node;

import net.somta.juggle.core.model.Method;

/**
 * 方法节点，主要承载接口请求
 *
 * @author husong
 * @date 2023/01/17
 */
public class MethodNode extends FlowNode {
    /**
     * 方法
     */
    private Method method;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
