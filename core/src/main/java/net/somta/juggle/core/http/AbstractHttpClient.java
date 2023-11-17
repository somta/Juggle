package net.somta.juggle.core.http;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.RequestTypeEnum;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
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

    private PoolingHttpClientConnectionManager connectionManager;
    public AbstractHttpClient() {
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100); // 设置最大连接数
        connectionManager.setDefaultMaxPerRoute(20); // 设置每个路由的最大连接数
    }


    @Override
    public Map<String, Object> sendRequest(Request request) {
        HttpUriRequestBase httpRequest = null;
        if (request.getRequestType() == RequestTypeEnum.GET) {
            httpRequest = new HttpGet(request.getRequestUrl());
        }else if (request.getRequestType() == RequestTypeEnum.DELETE) {
            httpRequest = new HttpDelete(request.getRequestUrl());
        } else if(request.getRequestType() == RequestTypeEnum.PUT) {
            httpRequest = new HttpPut(request.getRequestUrl());
        } else if(request.getRequestType() == RequestTypeEnum.POST) {
            httpRequest = new HttpPost(request.getRequestUrl());
        } else {
            throw new IllegalArgumentException("不支持的请求类型");
        }
        buildRequestParams(httpRequest,request);

        fillHttpHeader(httpRequest,request);
        fillHttpConfig(httpRequest,request);
        return handleHttpResponse(httpRequest,request);
    }

    private void fillHttpHeader(HttpUriRequestBase httpRequest, Request request){
        Map<String,Object> headers = request.getRequestHeaders();
        if(headers != null){
            for (Map.Entry<String, Object> header : headers.entrySet()) {
                httpRequest.addHeader(header.getKey(), header.getValue());
            }
        }
    }

    private void fillHttpConfig(HttpUriRequestBase httpRequest,Request request){
        RequestConfig.Builder builder = RequestConfig.custom();
        if(request.getTimeout() != null){
            // 设置响应超时时间
            builder.setResponseTimeout(Timeout.ofMilliseconds(request.getTimeout()));
        }
        httpRequest.setConfig(builder.build());
    }

    private Map<String, Object> handleHttpResponse(HttpUriRequestBase httpRequest, Request request){
        Map<String,Object> resultMap = new HashMap<>(8);
        final HttpClientResponseHandler<Map<String, Object>> responseHandler = response -> {
            HttpEntity entity = response.getEntity();
            String resultContent = EntityUtils.toString(entity);
            Map<String,Object> map = JsonSerializeHelper.deserialize(resultContent, Map.class);
            if(entity != null){
                EntityUtils.consume(entity);
            }
            return map;
        };

        CloseableHttpClient httpClient = HttpClients.custom()
                .setRetryStrategy(new CustomRetryStrategy(request)) // 设置默认的重试策略
                .setConnectionManager(connectionManager)
                .build();
        try {
            resultMap = httpClient.execute(httpRequest,responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    protected abstract void buildRequestParams(HttpUriRequestBase httpRequest, Request request);
}
