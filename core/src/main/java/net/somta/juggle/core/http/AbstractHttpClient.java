/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.core.http;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.RequestTypeEnum;
import net.somta.juggle.core.exception.FlowException;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * @author husong
 * @since 1.0.0
 */
public abstract class AbstractHttpClient implements IHttpClient{
    private final static Logger logger = LoggerFactory.getLogger(AbstractHttpClient.class);
    private PoolingHttpClientConnectionManager connectionManager;
    public AbstractHttpClient() {
        connectionManager = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        connectionManager.setMaxTotal(100);
        // 设置每个路由的最大连接数
        connectionManager.setDefaultMaxPerRoute(20);
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
        fillDefaultHttpHeader(httpRequest,request);
        Map<String,Object> headers = request.getRequestHeaders();
        if(headers != null){
            for (Map.Entry<String, Object> header : headers.entrySet()) {
                httpRequest.addHeader(header.getKey(), header.getValue());
            }
        }
    }

    private void fillDefaultHttpHeader(HttpUriRequestBase httpRequest, Request request){
        Package pkg = AbstractHttpClient.class.getPackage();
        String version = pkg.getImplementationVersion();
        if(StringUtils.isNotBlank(version)){
            httpRequest.addHeader("Juggle-Version", version);
        }
        String apiKey = System.getProperty("apiKey");
        httpRequest.addHeader("Api-Key", apiKey);
        httpRequest.addHeader("Api-Code", request.getApiCode());
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
            int statusCode = response.getCode();
            if (statusCode >= HttpStatus.SC_MULTIPLE_CHOICES) {
                logger.error("请求接口异常，接口地址:{},响应状态码：{}",request.getRequestUrl(),statusCode);
                return Collections.EMPTY_MAP;
            }
            HttpEntity entity = response.getEntity();
            String resultContent = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            Map<String,Object> map = JsonSerializeHelper.deserialize(resultContent, Map.class);
            if(entity != null){
                EntityUtils.consume(entity);
            }
            return map;
        };

        HttpClient httpClient = HttpClients.custom()
                .setRetryStrategy(new CustomRetryStrategy(request))
                .setConnectionManager(connectionManager)
                .build();
        try {
            resultMap = httpClient.execute(httpRequest,responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    protected abstract void buildRequestParams(HttpUriRequestBase httpRequest, Request request);
}
