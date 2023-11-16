package net.somta.juggle.core.http;

import net.somta.core.helper.JsonSerializeHelper;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author husong
 */
public abstract class AbstractHttpClient implements IHttpClient{

    protected CloseableHttpClient httpClient;
    public AbstractHttpClient() {
        this.httpClient = HttpClients.createDefault();
    }

    protected void fillHttpHeader(HttpUriRequestBase httpRequest, Request request){
        Map<String,String> headers = request.getRequestHeaders();
        if(headers != null){
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpRequest.addHeader(header.getKey(), header.getValue());
            }
        }
    }

    protected void fillHttpConfig(HttpUriRequestBase httpRequest,Request request){
        RequestConfig.Builder builder = RequestConfig.custom();
        if(request.getTimeout() != null){
            // 设置响应超时时间
            builder.setResponseTimeout(Timeout.ofMilliseconds(request.getTimeout()));
        }
        httpRequest.setConfig(builder.build());
    }

    protected Map<String, Object> handleHttpResponse(HttpUriRequestBase httpRequest){
        Map<String,Object> resultMap = new HashMap<>(8);
        final HttpClientResponseHandler<Map<String, Object>> responseHandler = response -> {
            HttpEntity entity = response.getEntity();
            String resultContent = EntityUtils.toString(entity);
            Map<String,Object> map = JsonSerializeHelper.deserialize(resultContent, Map.class);
            EntityUtils.consume(response.getEntity());
            return map;
        };

        try {
            resultMap = httpClient.execute(httpRequest,responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
