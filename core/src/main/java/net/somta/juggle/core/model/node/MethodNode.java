package net.somta.juggle.core.model.node;

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
}
