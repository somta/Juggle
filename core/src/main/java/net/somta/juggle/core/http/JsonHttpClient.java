package net.somta.juggle.core.http;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.RequestTypeEnum;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.Map;

/**
 * @author husong
 */
public class JsonHttpClient extends AbstractHttpClient{
    @Override
    public Map<String, Object> sendRequest(Request request) {
        HttpUriRequestBase httpRequest = null;
        if (request.getRequestType() == RequestTypeEnum.GET) {
            httpRequest = new HttpGet(request.getRequestUrl());
        } else if (request.getRequestType() == RequestTypeEnum.DELETE) {
            httpRequest = new HttpDelete(request.getRequestUrl());
        } else if(request.getRequestType() == RequestTypeEnum.PUT) {
            httpRequest = new HttpPut(request.getRequestUrl());
        } else if(request.getRequestType() == RequestTypeEnum.POST) {
            httpRequest = new HttpPost(request.getRequestUrl());
        } else {
            throw new IllegalArgumentException("不支持的请求类型");
        }
        if (request.getRequestParams() != null) {
            String jsonParam = JsonSerializeHelper.serialize(request.getRequestParams());
            StringEntity stringEntity = new StringEntity(jsonParam, ContentType.APPLICATION_JSON);
            httpRequest.setEntity(stringEntity);
        }

        super.fillHttpHeader(httpRequest,request);
        super.fillHttpConfig(httpRequest,request);
        return super.handleHttpResponse(httpRequest);
    }
}
